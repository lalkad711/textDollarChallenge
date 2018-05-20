import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class TextToDollar {

	private static List<String> digits = Arrays.asList("", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
			"Eight", "Nine", "Ten");

	private static List<String> tensList = Arrays.asList("", "", "Twenty", "Thirty", "Fourty", "Fifty", "Sixty",
			"Seventy", "Eighty", "Ninty");

	private static List<String> teensList = Arrays.asList("", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
			"Sixteen", "Seventeen", "Eighteen", "Ninteen");

	private static final String THOUSAND = "Thousand";
	private static final String HUNDRED = "Hundred";
	private static final String MILLION = "Million";
	private static final String BILLION = "Billion";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String textToConvert = sc.nextLine();
		sc.close();
		int length = textToConvert.toCharArray().length;
		int[] numArr = new int[length];
		IntStream.range(0, length).forEach(i -> {
			numArr[i] = textToConvert.toCharArray()[i]-48;
		});
		System.out.println(convertNumberToString(numArr));

	}

	private static String convertNumberToString(int[] numArr) {

//		char[] numArr = numberText.toCharArray();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < numArr.length; i++) {

			switch (numArr.length - (i + 1)) {

			case 0:
				unitsIndexData(i, sb, numArr);
				break;

			case 1:
				tensIndexData(i, sb, numArr);
				break;

			case 2:
				hundredsIndexData(i, sb, numArr);
				break;

			case 3:
				unitsIndexData(i, sb, numArr);
				sb.append(THOUSAND).append(" ");
				break;

			case 4:
				tensIndexData(i, sb, numArr);
				break;

			case 5:
				hundredsIndexData(i, sb, numArr);
				break;

			case 6:
				unitsIndexData(i, sb, numArr);
				sb.append(MILLION).append(" ");
				break;

			case 7:
				tensIndexData(i, sb, numArr);
				break;

			case 8:
				hundredsIndexData(i, sb, numArr);
				break;
				
			case 9:
				sb.append(digits.get(numArr[i])).append(" ").append(BILLION).append(" ");
				break;
			}
		}
		return sb.toString();
	}

	private static void unitsIndexData(int i, StringBuffer sb, int[] numArr) {
		if(i == 0) {
			sb.append(digits.get(numArr[i])).append(" ");
			return;
		}	
		if (numArr[i - 1] > 1)
			sb.append(digits.get(numArr[i])).append(" ");
		else if(numArr[i - 1] == 1 && numArr[i] == 0)
			sb.append(digits.get(10)).append(" ");
		else
			sb.append(teensList.get(numArr[i])).append(" ");
	}
	
	private static void tensIndexData(int i, StringBuffer sb, int[] numArr) {
		if (numArr[i] >= 2)
			sb.append(tensList.get(numArr[i])).append(" ");
	}
	
	private static void hundredsIndexData(int i, StringBuffer sb, int[] numArr) {
		sb.append(digits.get(numArr[i])).append(" ").append(HUNDRED).append(" And ");
	}

}
