package baekjoon.boj10825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException{
        Main main = new Main();
        main.solve();
    }

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCnt = Integer.parseInt(br.readLine());

        List<Student> studentList = new ArrayList<>();

        for(int i = 0 ; i < testCnt; i++){
            String[] tok = br.readLine().split(" ");
            Student student = new Student();
            student.name = tok[0];
            student.k = Integer.parseInt(tok[1]);
            student.e = Integer.parseInt(tok[2]);
            student.m = Integer.parseInt(tok[3]);
            studentList.add(student);
        }

        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.name.compareTo(o2.name);
            }
        });

        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return -Integer.compare(o1.m,o2.m);
            }
        });

        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o1.e,o2.e);
            }
        });

        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return -Integer.compare(o1.k,o2.k);
            }
        });

        for (Student student : studentList) {
            System.out.println(student.name);
        }
    }

    class Student{
        String name;
        int k;
        int e;
        int m;
    }
}
