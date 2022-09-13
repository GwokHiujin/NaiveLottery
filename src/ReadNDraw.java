import jxl.Sheet;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author guoyan
 */
public class ReadNDraw {
    private final ArrayList<Integer> ids;
    private final ArrayList<Integer> places;
    private final HashMap<Integer, Member> members;
    private final ArrayList<String> names;

    /* Weight Settings */

    private int FP_FW = 15, FP_SW = 39, FP_TW = 50;
    private int SP_FW = 10, SP_SW = 25, SP_TW = 43;
    private int TP_FW = 5, TP_SW = 13, TP_TW = 33;
    private int OP_FW = 1, OP_SW = 5, OP_TW = 15;
    private int OW = 100;

    private int RE_OW = 2, RE_TW = 6, RE_SW = 14, RE_FW = 30;

    private final ArrayList<Integer> firstPlace = new ArrayList<>();
    private final ArrayList<Integer> secondPlace = new ArrayList<>();
    private final ArrayList<Integer> thirdPlace = new ArrayList<>();

    public ReadNDraw() {
        ids = new ArrayList<>();
        places = new ArrayList<>();
        members = new HashMap<>();
        names = new ArrayList<>();
    }

    public void readIdFile(Sheet sheet) {
        for (int i = 1; i < sheet.getRows(); i++) {
            ids.add(Integer.parseInt(sheet.getCell(0, i).getContents()));
            names.add(sheet.getCell(1, i).getContents());
            places.add(Integer.parseInt(sheet.getCell(2, i).getContents()));
        }

        for (int i = 0; i < ids.size(); i++) {
            switch (places.get(i)) {
                case 1:
                    members.put(ids.get(i), new Member(ids.get(i), names.get(i), FP_FW, FP_SW, FP_TW, 1));
                    break;
                case 2:
                    members.put(ids.get(i), new Member(ids.get(i), names.get(i), SP_FW, SP_SW, SP_TW, 2));
                    break;
                case 3:
                    members.put(ids.get(i), new Member(ids.get(i), names.get(i), TP_FW, TP_SW, TP_TW, 3));
                    break;
                default:
                    members.put(ids.get(i), new Member(ids.get(i), names.get(i), OP_FW, OP_SW, OP_TW, 4));
                    break;
            }
        }

        runLottery();
    }

    private void runLottery() {
        int firstAmount = 1;
        int secondAmount = 2;
        int thirdAmount = 3;

        firstPlace.clear();
        secondPlace.clear();
        thirdPlace.clear();

        while (firstPlace.size() < firstAmount ||
                secondPlace.size() < secondAmount ||
                thirdPlace.size() < thirdAmount) {
            for (int id : members.keySet()) {
                Member member = members.get(id);
                if (member.getPlace() != -1) {
                    continue;
                }
                double curRandom = Math.random() * OW;
                member.lotteryPlace(curRandom);
                switch (member.getPlace()) {
                    case 1:
                        firstPlace.add(member.getID());
                        break;
                    case 2:
                        secondPlace.add(member.getID());
                        break;
                    case 3:
                        thirdPlace.add(member.getID());
                        break;
                    default:
                        break;
                }
            }
        }

        if (firstPlace.size() > firstAmount) {
            reLottery(firstPlace, 1);
        }
        if (secondPlace.size() > secondAmount) {
            reLottery(secondPlace, 2);
        }
        if (thirdPlace.size() > thirdAmount) {
            reLottery(thirdPlace, 3);
        }

        System.out.println("=============== 抽奖结束 ===============");
        System.out.println("获得一等奖的同学是：【" + members.get(firstPlace.get(0)).getName() +
                "】【" + members.get(firstPlace.get(0)).getID() + "】");
        System.out.println("获得二等奖的同学是：【" + members.get(secondPlace.get(0)).getName() +
                "】【" + members.get(secondPlace.get(0)).getID() + "】，【" + members.get(secondPlace.get(1)).getName() +
                "】【" + members.get(secondPlace.get(1)).getID() + "】");
        System.out.println("获得三等奖的同学是：【" + members.get(thirdPlace.get(0)).getName() +
                "】【" + members.get(thirdPlace.get(0)).getID() + "】，【" + members.get(thirdPlace.get(1)).getName() +
                "】【" + members.get(thirdPlace.get(1)).getID() + "】，【" + members.get(thirdPlace.get(2)).getName() +
                "】【" + members.get(thirdPlace.get(2)).getID() + "】");
        System.out.println("Congratulations!*★,°*:.☆(￣▽￣)/$:*.°★* 。");
    }

    private void reLottery(ArrayList<Integer> targetMembers, int limit) {
        ArrayList<Integer> ans = new ArrayList<>();

        while (ans.size() < limit) {
            for (int i = 0; i < limit; i++) {
                int targetOriginPlace;
                double curRandom = Math.random() * RE_FW;
                if (0 < curRandom && curRandom <= RE_OW) {
                    targetOriginPlace = 4;
                } else if (RE_OW < curRandom && curRandom <= RE_TW) {
                    targetOriginPlace = 3;
                } else if (RE_TW < curRandom && curRandom <= RE_SW) {
                    targetOriginPlace = 2;
                } else {
                    targetOriginPlace = 1;
                }

                ArrayList<Integer> toBeChosen = new ArrayList<>();

                for (int id : targetMembers) {
                    if (members.get(id).getOriginPlace() == targetOriginPlace && !ans.contains(id)) {
                        toBeChosen.add(id);
                    }
                }
                if (toBeChosen.isEmpty()) {
                    continue;
                }

                curRandom = Math.random() * toBeChosen.size();
                ans.add(toBeChosen.get((int) Math.floor(curRandom)));
            }
        }

        targetMembers.clear();
        targetMembers.addAll(ans);
    }

    public ArrayList<Member> getPrizedMember(int placeNum) {
        ArrayList<Member> ans = new ArrayList<>();
        switch (placeNum) {
            case 1:
                for (int id : firstPlace) {
                    ans.add(members.get(id));
                }
                break;
            case 2:
                for (int id : secondPlace) {
                    ans.add(members.get(id));
                }
                break;
            case 3:
                for (int id : thirdPlace) {
                    ans.add(members.get(id));
                }
                break;
            default:
                break;
        }
        return ans;
    }
}
