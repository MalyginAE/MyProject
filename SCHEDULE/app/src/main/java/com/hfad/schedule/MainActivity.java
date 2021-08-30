package com.hfad.schedule;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends Activity {
    static Map<Integer, List<Lesson>> bells = new HashMap<>();
    static List<Yrok> class10 = new ArrayList<>();
    static List<Yrok> class11 = new ArrayList<>();
    List<String> week_day = new ArrayList<>();

    public void DayOfWeek1() {
        week_day.addAll(Arrays.asList("Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"));
    }

    public static void eleven_class() {
        class11.add(new Yrok(Arrays.asList(
                "Химия ф.",
                "Литература",
                "Алгебра",
                "Геоография",
                "ИВТ",
                "Черчение",
                "Ин.яз."
        )));
        class11.add(new Yrok(Arrays.asList("ИВТ",
                "ОБЩ ф.",
                "Биология",
                "Истерия",
                "Алгебра",
                "Ин.яз.",
                "Литература"
        )));
        class11.add(new Yrok(Arrays.asList("Литература",
                "Ф-ра",
                "Геометрия",
                "Геометрия",
                "МХК",
                "ОБЩ",
                "ИВТ"
        )));
        class11.add(new Yrok(Arrays.asList("Русский язык",
                "Русский язык",
                "Ф-ра",
                "физика",
                "физика",
                "алгебра", null
        )));
        class11.add(new Yrok(Arrays.asList(null,
                "ИстЕрия",
                "ИВТ",
                "ф-ра",
                "Алгебра",
                "Химия",
                "Ин-яз"
        )));
        class11.add(new Yrok(Arrays.asList("ОБЖ",
                "ОБЖ",
                "Физика",
                "ОБЩ ф.",
                null,
                null,
                null
        )));

    }

    public static void ten_class() {
        class10.add(new Yrok(Arrays.asList(null,
                "Черчение",
                "Астрономия",
                "Алгебра",
                "Истерия",
                "Ин-яз",
                "ОБЩ ф.")));
        class10.add(new Yrok(Arrays.asList("Биология",
                "Геометрия",
                "ИВТ",
                "Физ-ра",
                "Ин-яз",
                "Литература",
                null)));
        class10.add(new Yrok(Arrays.asList("Биология ф.",
                "Биология ф.",
                "Алгебра",
                "ОБЩ",
                "Физ-ра",
                "География",
                null)));
        class10.add(new Yrok(Arrays.asList("Русский язык",
                "Литература",
                "Алгебра",
                "Ин. яз.",
                "МХК",
                "Физика",
                "Физика")));
        class10.add(new Yrok(Arrays.asList("ИВТ",
                "Литература",
                "История",
                "Фин.гр.",
                "Геометрия",
                "Ф-ра",
                "Химия")));
        class10.add(new Yrok(Arrays.asList("Физика",
                "физика",
                "ОБЖ",
                "ОБЖ",
                "ОБЩ ф.",
                null,
                null)));


    }

    public static class Yrok {
        List<String> list = new ArrayList<>();

        public Yrok(List<String> list) {
            this.list = list;
        }
    }


    public void callMethod() {
        doTest();
        ten_class();
        DayOfWeek1();
        Days_of_Week();


    }

    private static class Lesson {
        int startTime;
        int duration;

        public Lesson(Integer start, Integer duration) {
            this.startTime = start;
            this.duration = duration;
        }
    }

    public void onMethod() {
        final TextView time = (TextView) findViewById(R.id.time_before);
        final TextView tech = (TextView) findViewById(R.id.tech);
        final TextView before = (TextView) findViewById(R.id.time_low);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                Date date = new Date();
                int day = date.getDay();
                List<Lesson> list = bells.get(day);

                for ( int i = 0; i < list.size(); i++) {
                    int minutes = date.getMinutes() + date.getHours() * 60;
                    Lesson lesson = list.get(i);
                    int начало_урокаnext = list.get(list.size()-1).startTime;
                    if (i+1<list.size()){
                    Lesson lesson1 = list.get(i+1);
                    начало_урокаnext = lesson1.startTime;}

                    int начало_урока = lesson.startTime;
                    int конец_урока = lesson.duration;
                    if ((minutes < начало_урокаnext)&&(minutes > конец_урока)){
                        String as2 = "До начала следующего урока осталось " + (начало_урокаnext - minutes) + " минут";
                        before.setText(as2);
                        String as = "Следующий урок " + (class10.get(day - 1).list.get(i + 1));
                        time.setText(as);
                        }
                    if ((minutes >= начало_урока) && (minutes <= конец_урока)) {
                        String as2 = "До начала следующего урока осталось " + (bells.get(day).get(i + 1).startTime - minutes) + " минут";
                        before.setText(as2);
                        String as1 = "До конца урока осталось: " + (конец_урока - minutes) + " минут";
                        tech.setText(as1);
                        String as = "Следующий урок " + (class10.get(day - 1).list.get(i + 1));
                        time.setText(as);
                        break;
                    }
                    else if (minutes < начало_урока){
                        int k = 0;
                        String as2 = "До начала первого урока осталось " + (list.get(k).startTime - minutes) + " минут";
                        before.setText(as2);
                        String as = "Первый урок " + (class10.get(day - 1).list.get(0));
                        time.setText(as);
                    }
                    else {
                        String as2 = "До начала первого урока осталось " + (24*60 - minutes+bells.get(day+1).get(0).startTime) + " минут";
                        before.setText(as2);
                        String as = "Первый урок " + (class10.get(day).list.get(0));
                        time.setText(as);
                    }




                }
                TextView view = (TextView) findViewById(R.id.clock);
                int hours = date.getHours();
                int minutes = date.getMinutes();
                int secs = date.getSeconds();
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                view.setText(time);
                handler.postDelayed(this, 1);
            }


        });
    }


    public static void doTest() {
        bells.put(1, Arrays.asList(
                new Lesson(8 * 60 + 38, 9 * 60 + 18),
                new Lesson(9 * 60 + 33, 10 * 60 + 13),
                new Lesson(10 * 60 + 28, 11 * 60 + 8),
                new Lesson(11 * 60 + 23, 12 * 60 + 3),
                new Lesson(12 * 60 + 23, 13 * 60 + 3),
                new Lesson(14 * 60 - 2, 14 * 60 + 38)));

        bells.put(2, Arrays.asList(
                new Lesson(8 * 60 - 2, 8 * 60 + 38),
                new Lesson(8 * 60 + 53, 9 * 60 + 33),
                new Lesson(9 * 60 + 48, 10 * 60 + 28),
                new Lesson(10 * 60 + 43, 11 * 60 + 23),
                new Lesson(12 * 60 + 33, 13 * 60 + 13),
                new Lesson(13 * 60 + 23, 14 * 60 + 3)));
        bells.put(3, Arrays.asList(
                new Lesson(8 * 60 - 2, 8 * 60 + 38),
                new Lesson(8 * 60 + 53, 9 * 60 + 33),
                new Lesson(9 * 60 + 48, 10 * 60 + 28),
                new Lesson(10 * 60 + 43, 11 * 60 + 23),
                new Lesson(12 * 60 + 33, 13 * 60 + 13),
                new Lesson(13 * 60 + 23, 14 * 60 + 3)));

        bells.put(4, Arrays.asList(
                new Lesson(8 * 60 - 2, 8 * 60 + 38),
                new Lesson(8 * 60 + 53, 9 * 60 + 33),
                new Lesson(9 * 60 + 48, 10 * 60 + 28),
                new Lesson(10 * 60 + 43, 11 * 60 + 23),
                new Lesson(12 * 60 + 33, 13 * 60 + 13),
                new Lesson(13 * 60 + 23, 14 * 60 + 3)));

        bells.put(5, Arrays.asList(
                new Lesson(8 * 60 - 2, 8 * 60 + 38),
                new Lesson(8 * 60 + 53, 9 * 60 + 33),
                new Lesson(9 * 60 + 48, 10 * 60 + 28),
                new Lesson(10 * 60 + 43, 11 * 60 + 23),
                new Lesson(12 * 60 + 33, 13 * 60 + 13),
                new Lesson(13 * 60 + 23, 14 * 60 + 3)));

        bells.put(6, Arrays.asList(
                new Lesson(8 * 60 - 2, 8 * 60 + 38),
                new Lesson(8 * 60 + 43, 9 * 60 + 23),
                new Lesson(9 * 60 + 28, 10 * 60 + 8),
                new Lesson(10 * 60 + 3, 10 * 60 + 53),
                new Lesson(11 * 60 - 2, 11 * 60 + 38),
                new Lesson(11 * 60 + 43, 12 * 60 + 23)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callMethod();
        onMethod();
        Image();


    }

    public void Image() {
        int as = (int) (Math.random() * 6);
        ImageView view = (ImageView) findViewById(R.id.image1);
        if (as == 1)
            view.setImageResource(R.drawable.g);
        else if (as == 2)
            view.setImageResource(R.drawable.b);
//        else if (as == 3)
//            view.setImageResource(R.drawable.c);
        else if (as == 4)
            view.setImageResource(R.drawable.d);
        else if (as == 5)
            view.setImageResource(R.drawable.e);
        else if (as == 6)
            view.setImageResource(R.drawable.f);
    }

    public void Days_of_Week() {
        Date date = new Date();
        TextView dayweek = (TextView) findViewById(R.id.day_of_the_week);


        TextView one = (TextView) findViewById(R.id.первый);
        TextView two = (TextView) findViewById(R.id.второй);
        TextView free = (TextView) findViewById(R.id.третий);
        TextView fo = (TextView) findViewById(R.id.четвертый);
        TextView five = (TextView) findViewById(R.id.пятый);
        TextView six = (TextView) findViewById(R.id.шестой);
        TextView seven = (TextView) findViewById(R.id.седьмой);

        int day = date.getDay();
        dayweek.setText(week_day.get(day - 1));
        Yrok yrok = class10.get(day - 1);
        for (int i = 0; i < yrok.list.size(); i++) {
            if (i == 0) {
                one.setText(yrok.list.get(i));
            }
            if (i == 1) {
                two.setText(yrok.list.get(i));
            }
            if (i == 2) {
                free.setText(yrok.list.get(i));
            }
            if (i == 3) {
                fo.setText(yrok.list.get(i));
            }
            if (i == 4) {
                five.setText(yrok.list.get(i));
            }
            if (i == 5) {
                six.setText(yrok.list.get(i));
            }
            if (i == 7) {
                seven.setText(yrok.list.get(i));
            }
        }


    }

    public void output(View view) {
        finish();
    }
}
