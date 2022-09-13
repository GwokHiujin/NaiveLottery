import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author guoyan
 */
public class RunLottery {

    public static void main(String[] args) throws IOException, BiffException {
        Workbook workbook = Workbook.getWorkbook(new File("data.xls"));

        ReadNDraw run = new ReadNDraw();
        run.readIdFile(workbook.getSheet(0));

        //runTest();
    }

    private static void runTest() throws BiffException, IOException {
        Workbook workbook = Workbook.getWorkbook(new File("data.xls"));

        int firstFromFirst = 0;
        int firstFromSecond = 0;
        int firstFromThird = 0;
        int firstFromNone = 0;

        int secondFromFirst = 0;
        int secondFromSecond = 0;
        int secondFromThird = 0;
        int secondFromNone = 0;

        int thirdFromFirst = 0;
        int thirdFromSecond = 0;
        int thirdFromThird = 0;
        int thirdFromNone = 0;

        int testNum = 10000;

        ArrayList<Member> firstMembers = new ArrayList<>();
        ArrayList<Member> secondMembers = new ArrayList<>();
        ArrayList<Member> thirdMembers = new ArrayList<>();

        for (int i = 0; i < testNum; i++) {
            ReadNDraw run = new ReadNDraw();
            run.readIdFile(workbook.getSheet(0));

            firstMembers.clear();
            secondMembers.clear();
            thirdMembers.clear();
            firstMembers.addAll(run.getPrizedMember(1));
            secondMembers.addAll(run.getPrizedMember(2));
            thirdMembers.addAll(run.getPrizedMember(3));

            for (Member member : firstMembers) {
                switch (member.getOriginPlace()) {
                    case 1:
                        firstFromFirst++;
                        break;
                    case 2:
                        firstFromSecond++;
                        break;
                    case 3:
                        firstFromThird++;
                        break;
                    default:
                        firstFromNone++;
                        break;
                }
            }

            for (Member member : secondMembers) {
                switch (member.getOriginPlace()) {
                    case 1:
                        secondFromFirst++;
                        break;
                    case 2:
                        secondFromSecond++;
                        break;
                    case 3:
                        secondFromThird++;
                        break;
                    default:
                        secondFromNone++;
                        break;
                }
            }

            for (Member member : thirdMembers) {
                switch (member.getOriginPlace()) {
                    case 1:
                        thirdFromFirst++;
                        break;
                    case 2:
                        thirdFromSecond++;
                        break;
                    case 3:
                        thirdFromThird++;
                        break;
                    default:
                        thirdFromNone++;
                        break;
                }
            }
        }

        System.out.println("=====================================TEST======================================");

        System.out.println("积分赛第一名队员抽中一等奖的概率是：" + (double) firstFromFirst / (double) (6 * testNum));
        System.out.println("积分赛第一名队员抽中二等奖的概率是：" + (double) secondFromFirst / (double) (6 * testNum));
        System.out.println("积分赛第一名队员抽中三等奖的概率是：" + (double) thirdFromFirst / (double) (6 * testNum));
        System.out.println("===============================================================================");

        System.out.println("积分赛第二名队员抽中一等奖的概率是：" + (double) firstFromSecond / (double) (6 * testNum));
        System.out.println("积分赛第二名队员抽中二等奖的概率是：" + (double) secondFromSecond / (double) (6 * testNum));
        System.out.println("积分赛第二名队员抽中三等奖的概率是：" + (double) thirdFromSecond / (double) (6 * testNum));
        System.out.println("===============================================================================");

        System.out.println("积分赛第三名队员抽中一等奖的概率是：" + (double) firstFromThird / (double) (6 * testNum));
        System.out.println("积分赛第三名队员抽中二等奖的概率是：" + (double) secondFromThird / (double) (6 * testNum));
        System.out.println("积分赛第三名队员抽中三等奖的概率是：" + (double) thirdFromThird / (double) (6 * testNum));
        System.out.println("===============================================================================");

        System.out.println("其余队员抽中一等奖的概率是：" + (double) firstFromNone / (double) (6 * testNum));
        System.out.println("其余队员抽中二等奖的概率是：" + (double) secondFromNone / (double) (6 * testNum));
        System.out.println("其余队员抽中三等奖的概率是：" + (double) thirdFromNone / (double) (6 * testNum));
    }
}
