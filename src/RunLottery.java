import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

public class RunLottery {
    public static void main(String[] args) throws IOException, BiffException {
        Workbook workbook = Workbook.getWorkbook(new File("data.xls"));
        ReadNDraw run = new ReadNDraw();
        run.readIdFile(workbook.getSheet(0));
    }
}
