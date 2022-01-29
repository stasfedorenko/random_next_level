package com.random.helperClass;

import com.random.entity.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RandomHelper {

    public static Student randomId(List<Student> list) {
        List<Student> doNotRepeat = new ArrayList<>();
        Student student = new Student();
        int maxCountMemberInGroup = 0;
        int count = 0;

        for (int i = 1; i <= list.size(); i++) {
            if (count == 0) {
                count++;
            } else if (list.get(i-1).getB_teamId() == list.get(i-2).getB_teamId()) {
                count++;
            } else {
                if (maxCountMemberInGroup < count) {
                    maxCountMemberInGroup = count;
                }
                count = 1;
            }
            if (i == list.size() && (maxCountMemberInGroup < count)) {
                maxCountMemberInGroup = count;
            }
        }

        //   list.sort(Comparator.comparingInt(Student::getB_teamId));

        while (doNotRepeat.size() != list.size()) {
            int check = (int) ((Math.random() * (list.size())));
            if (!doNotRepeat.contains(list.get(check))) {
                if (doNotRepeat.size() >= list.size() - maxCountMemberInGroup) {
                    student = list.get(check);
                    doNotRepeat.add(list.get(check));
                } else {
                    if((doNotRepeat.isEmpty())){
                        student = list.get(check);
                        doNotRepeat.add(list.get(check));
                    }
                    if ((doNotRepeat.get(doNotRepeat.size() - 1).getB_teamId() != list.get(check).getB_teamId())) {
                        student = list.get(check);
                        doNotRepeat.add(list.get(check));
                    }
                }
            }
        }
        return student;
    }
}
