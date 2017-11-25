import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.IImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;

@SuppressWarnings("serial")
public class Loadfile extends JPanel implements DropTargetListener {
	DropTarget dt;
	String filelocaion;
	File path = new File(".");
	File file;
	String filetype = new String();
	String filename = new String();
	StringTokenizer st;// Ȯ���� �и�
	StringTokenizer st2;// ���ϸ� �и�
	InputStream is = null;
	BufferedInputStream bis = null;
	OutputStream os = null;
	BufferedOutputStream bos = null;
	int tempcopy;// ���Ϻ���� temp
	int status;// ���Ϻ������� (close ó��)
	int dropstatus;// ��� ����
	String taken;
	String[] compare;
	int date;
	int time;

	public Loadfile() {

		dt = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null);
		this.setSize(800, 550);
		this.setOpaque(false);
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		if (dropstatus != 1)
			System.out.println("dragEnter");
		dropstatus = 1;
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		if (dropstatus != 2)
			System.out.println("dragExit");
		dropstatus = 2;
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		if (dropstatus != 3)
			System.out.println("dragOver");
		dropstatus = 3;
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {

		if (dropstatus != 4)
			System.out.println("dragDrop");
		dropstatus = 4;
		if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
			dtde.acceptDrop(dtde.getDropAction());
			Transferable tr = dtde.getTransferable();

			try {
				// ���ϸ� ������
				@SuppressWarnings("rawtypes")
				java.util.List list = (java.util.List) tr.getTransferData(DataFlavor.javaFileListFlavor);

				// ���ϸ� ���
				for (int i = 0; i < list.size(); i++)// ��ӵ� ������ ������ŭ �ݺ�
				{
					filelocaion = list.get(i).toString();// ��κҷ�����
					System.out.println("file location:" + filelocaion);
					file = new File(filelocaion);
					st = new StringTokenizer(filelocaion, ".");// Ȯ���ڸ� �и�
					while (st.hasMoreTokens())// ������ ��ū�� ��ȯ(Ȯ����)
					{
						filetype = st.nextToken();
					}
					st2 = new StringTokenizer(filelocaion, "\\");// �����̸��� �и�
					while (st2.hasMoreTokens()) {
						filename = st2.nextToken();
					}

					if (filetype.equals("jpg") || filetype.equals("jpeg") || filetype.equals("JPG")
							|| filetype.equals("JPEG"))// ����
					{
						status = 0;
						System.out.println("image confirmed");
						System.out.println("file name :" + filename);

						// ���� ��Ÿ������ �ҷ�����
						IImageMetadata metadata = Imaging.getMetadata(file);
						try {// exif������ �������� ����
							if (metadata instanceof JpegImageMetadata) {
								JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
								taken = jpegMetadata
										.findEXIFValueWithExactMatch(ExifTagConstants.EXIF_TAG_DATE_TIME_ORIGINAL)
										.getStringValue();
								compare = taken.split(":");
								String[] temp = compare[2].split(" ");
								date = Integer.parseInt(compare[0]) * 10000 + Integer.parseInt(compare[1]) * 100
										+ Integer.parseInt(temp[0]);// yyyymmdd
								time = Integer.parseInt(temp[1]) * 10000 + Integer.parseInt(compare[3]) * 100
										+ Integer.parseInt(compare[4]);// hhmmss
								System.out.println(TTdataException.ekscnr.size());
								//System.out.println(TTdataException.ekscnr);

								for (int c = 0; c < TTdataException.ekscnr.size(); c++) {
									System.out.println(c+"��° ����ó���� ��¥�� ��");
									if (Integer.parseInt(TTdataException.ekscnr.get(c).ExceptedDATE)==date)// ����ó���� ��¥�϶�
									{
										System.out.println("����ó����");
										for (int s = 0; s < 7; s++) {
											if (TTdataException.ekscnr.get(c).getstarttime(s + 1) < time
													&& time < TTdataException.ekscnr.get(c).getendtime(s + 1))// ���
											{
												System.out.println(String.valueOf(s+1) + "����");
												DateFormat a = new SimpleDateFormat("yyyyMMdd");
												Date skf = a.parse(String.valueOf(date));
												Calendar cal = Calendar.getInstance();
												cal.setTime(skf);
												int dayNum = cal.get(Calendar.DAY_OF_WEEK);// ��������
												if (dayNum == 1 || dayNum == 7)
													break;
												copypic(TTdata.subjects[s][dayNum-2]);
												status =1;
												break;
											}
										}
										break;
									}
									
								}
								if(status==0)
								{
									System.out.println("����ƽ������");
									for (int s = 0; s < 7; s++) {
										if (TTdata.getstarttime(s + 1) < time && time < TTdata.getendtime(s + 1))// ���
										{
											System.out.println(String.valueOf(s+1) + "����");
											DateFormat a = new SimpleDateFormat("yyyyMMdd");
											Date skf = a.parse(String.valueOf(date));
											Calendar cal = Calendar.getInstance();
											cal.setTime(skf);
											int dayNum = cal.get(Calendar.DAY_OF_WEEK);// ��������
											if (dayNum == 1 || dayNum == 7)
												break;
											System.out.println(TTdata.subjects[s][dayNum - 2]);
											copypic(TTdata.subjects[s][dayNum - 2]);
											break;
										}
									}
									break;
								}
								
							}
						} catch (Exception e) {
							//System.out.println(e.toString());
							String tempoutput = new String();
							System.out.println(filename);
							tempoutput = filename + " ��(��)\n EXIF�� ���� �����Դϴ�.";
							JOptionPane.showMessageDialog(null, tempoutput);// ����
						}

					} else {
						String tempoutput = new String();
						System.out.println(filename);
						tempoutput = filename + " ��(��)\n ���������� �ƴϰų� �������� �ʴ� Ȯ�����Դϴ�.";
						JOptionPane.showMessageDialog(null, tempoutput);// ����
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		if (dropstatus != 5)
			System.out.println("dragActionChanged");
		dropstatus = 5;
	}

	public void copypic(String subject) throws IOException {
		is = new FileInputStream(filelocaion);// ó���ӵ� ���
		bis = new BufferedInputStream(is);
		os = new FileOutputStream(path.getAbsolutePath() + "\\UploadedFiles\\" + "\\" + subject + "\\" + filename);
		bos = new BufferedOutputStream(os);
		while (true)// ���Ϻ���
		{
			tempcopy = bis.read();
			if (tempcopy == -1) {
				System.out.println("�����");
				status = 1;
				break;
			}
			bos.write(tempcopy);
		}

		if (status == 1) {
			try {
				is.close();
				status = 0;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				bis.close();
				status = 0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				os.close();
				status = 0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bis.close();
				status = 0;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}