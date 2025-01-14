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
	StringTokenizer st;// 확장자 분리
	StringTokenizer st2;// 파일명 분리
	InputStream is = null;
	BufferedInputStream bis = null;
	OutputStream os = null;
	BufferedOutputStream bos = null;
	int tempcopy;// 파일복사시 temp
	int status;// 파일복사유무 (close 처리)
	int dropstatus;// 드롭 상태
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
				// 파일명 얻어오기
				@SuppressWarnings("rawtypes")
				java.util.List list = (java.util.List) tr.getTransferData(DataFlavor.javaFileListFlavor);
				System.out.println("droped file count: "+list.size());
				// 파일명 출력
				for (int i = 0; i < list.size(); i++)// 드롭된 아이템 갯수만큼 반복
				{
					filelocaion = list.get(i).toString();// 경로불러오기
					System.out.println("file location: " + filelocaion);
					file = new File(filelocaion);
					st = new StringTokenizer(filelocaion, ".");// 확장자만 분리
					while (st.hasMoreTokens())// 마지막 토큰을 반환(확장자)
					{
						filetype = st.nextToken();
					}
					st2 = new StringTokenizer(filelocaion, "\\");// 파일이름만 분리
					while (st2.hasMoreTokens()) {
						filename = st2.nextToken();
					}

					if (filetype.equals("jpg") || filetype.equals("jpeg") || filetype.equals("JPG")
							|| filetype.equals("JPEG"))// 파일
					{
						status = 0;
						System.out.println("image confirmed");
						System.out.println("file name: " + filename);

						// 파일 메타데이터 불러오기
						IImageMetadata metadata = Imaging.getMetadata(file);
						try {// exif정보가 없을때를 위함
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
								System.out.println("excepted date count: "+TTdataException.ekscnr.size());
								//System.out.println(TTdataException.ekscnr);

								for (int c = 0; c < TTdataException.ekscnr.size(); c++) {
									System.out.println(c+"번째 예외처린된 날짜와 비교");
									if (Integer.parseInt(TTdataException.ekscnr.get(c).ExceptedDATE)==date)// 예외처리된 날짜일때
									{
										System.out.println("예외처리됨");
										for (int s = 0; s < 7; s++) {
											if (TTdataException.ekscnr.get(c).getstarttime(s + 1) < time
													&& time < TTdataException.ekscnr.get(c).getendtime(s + 1))// 몇교시
											{
												System.out.println(String.valueOf(s+1) + "교시");
												DateFormat a = new SimpleDateFormat("yyyyMMdd");
												Date skf = a.parse(String.valueOf(date));
												Calendar cal = Calendar.getInstance();
												cal.setTime(skf);
												int dayNum = cal.get(Calendar.DAY_OF_WEEK);// 무슨요일
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
									System.out.println("스태틱사진임");
									for (int s = 0; s < 7; s++) {
										if (TTdata.getstarttime(s + 1) < time && time < TTdata.getendtime(s + 1))// 몇교시
										{
											System.out.println(String.valueOf(s+1) + "교시");
											DateFormat a = new SimpleDateFormat("yyyyMMdd");
											Date skf = a.parse(String.valueOf(date));
											Calendar cal = Calendar.getInstance();
											cal.setTime(skf);
											int dayNum = cal.get(Calendar.DAY_OF_WEEK);// 무슨요일
											if (dayNum == 1 || dayNum == 7)
												break;
											System.out.println(TTdata.subjects[s][dayNum - 2]);
											copypic(TTdata.subjects[s][dayNum - 2]);
											break;
										}
									}
								}
								
							}
						} catch (Exception e) {
							//System.out.println(e.toString());
							String tempoutput = new String();
							System.out.println(filename);
							tempoutput = filename + " 은(는)\n EXIF가 없는 사진입니다.";
							JOptionPane.showMessageDialog(null, tempoutput);// 예외
						}

					} else {
						String tempoutput = new String();
						System.out.println(filename);
						tempoutput = filename + " 은(는)\n 사진파일이 아니거나 지원하지 않는 확장자입니다.";
						JOptionPane.showMessageDialog(null, tempoutput);// 예외
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
		is = new FileInputStream(filelocaion);// 처리속도 향상
		bis = new BufferedInputStream(is);
		os = new FileOutputStream(path.getAbsolutePath() + "\\UploadedFiles\\" + "\\" + subject + "\\" + filename);
		bos = new BufferedOutputStream(os);
		while (true)// 파일복사
		{
			tempcopy = bis.read();
			if (tempcopy == -1) {
				System.out.println("복사됨");
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