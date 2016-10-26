import java.util.*;

public class KNN {
	public static void main (String args[]) {
		Scanner terminal = new Scanner(System.in);

		System.out.print("We will be using data with two parameters, X1 (Integer) and X2 (Integer). Each entry in the data will have a class associated with it.\nHow many entries do you have for training? ");
		int n = Integer.parseInt(terminal.nextLine());

		Record record_arr[] = new Record[n];

		System.out.println("Enter data:");
		for (int i = 0; i < n; i++) {
			int x1, x2;
			String className;
			System.out.print("X1: ");
			x1 = Integer.parseInt(terminal.nextLine());
			System.out.print("X2: ");
			x2 = Integer.parseInt(terminal.nextLine());
			System.out.print("Class: ");
			className = terminal.nextLine();
			Record record = new Record(x1, x2, className);
			record_arr[i] = record;
		}

		System.out.print("What do you want to set K as? ");
		int k = Integer.parseInt(terminal.nextLine());

		System.out.print("We will now predict the class based on X1 (Integer) and X2 (Integer).\nX1: ");
		int pred_x1 = Integer.parseInt(terminal.nextLine());
		System.out.print("X2: ");
		int prex_x2 = Integer.parseInt(terminal.nextLine());

		int distances[] = new int[n];
		for (int i = 0; i < n; i++) {
			distances[i] = record_arr[i].distanceFrom(pred_x1, prex_x2);
		}

		for (int i = 0, j = 0; i < n; i++) {
			int min = i;
			for (j = i; j < n; j++) {
				if (distances[j] < distances[min]) {
					min = j;
				}
			}
			if (i != min && min < n) {
				int tmp = distances[i];
				distances[i] = distances[min];
				distances[min] = tmp;

				Record tmp_record = record_arr[i];
				record_arr[i] = record_arr[min];
				record_arr[min] = tmp_record;
			}
		}

		HashSet<String> class_set = new HashSet<String>();
		for (int i = 0; i < k; i++) {
			String className = record_arr[i].className;
			if (!class_set.contains(className)) {
				class_set.add(className);
			}
		}

		int num_classes = class_set.size();
		Iterator<String> iterator = class_set.iterator();
		String classes[] = new String[num_classes];
		int classes_times[] = new int[num_classes];
		for (int i = 0; i < num_classes; i++) {
			classes_times[i] = 0;
			classes[i] = iterator.next();
		}

		for (int i = 0; i < k; i++) {
			String className = record_arr[i].className;
			for (int j = 0; j < classes.length; j++) {
				if (className.equals(classes[j])) {
					classes_times[j]++;
				}
			}
		}

		System.out.println("\nProbabilities:");
		for (int i = 0; i < num_classes; i++) {
			System.out.println(classes[i] + ": " + (((float) classes_times[i]/ (float) k) * 100) + "%");
		}
	}
}