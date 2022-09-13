import java.util.ArrayList;

public class Member {
    private final int ID;
    private final String name;
    private final int firstPlaceWeight;
    private final int secondPlaceWeight;
    private final int thirdPlaceWeight;
    private final int originPlace;
    private int lotteryPlace;

    public Member(int id, String name, int first, int second, int third, int originPlace) {
        ID = id;
        this.name = name;
        firstPlaceWeight = first;
        secondPlaceWeight = second;
        thirdPlaceWeight = third;
        this.originPlace = originPlace;
        lotteryPlace = -1;
    }

    public void lotteryPlace(double randomNumber) {
        if (0 < randomNumber && randomNumber <= firstPlaceWeight) {
            lotteryPlace =  1;
        } else if (firstPlaceWeight < randomNumber && randomNumber <= secondPlaceWeight) {
            lotteryPlace =  2;
        } else if (secondPlaceWeight < randomNumber && randomNumber <= thirdPlaceWeight) {
            lotteryPlace =  3;
        }
    }

    public int getID() {
        return ID;
    }

    public int getPlace() {
        return lotteryPlace;
    }

    public String getName() {
        return name;
    }

    public int getOriginPlace() {
        return originPlace;
    }
}
