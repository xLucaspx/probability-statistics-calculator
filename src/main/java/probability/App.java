package probability;

import probability.calculation.MediaGeometrica;
import probability.calculation.MediaHarmonica;

import java.util.List;

public class App {
	public static void main(String... args) {
		System.out.println("Hello, world!");
		System.out.println(MediaGeometrica.calculaMediaGeometrica(List.of(4,2,8)));
	}
}
