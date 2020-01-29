package pl.itacademy.schedule;

import org.apache.commons.cli.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class InputParametersReader {
    public LessonParameters readParameters(String[] args) throws ParseException {
        Options options = new Options();

        options.addOption("n", true, "number of required hours");
        options.addOption("f", true, "file name");
        options.addOption("h", false, "show help");
        options.addOption("d", true, "lesson days");
        options.addOption("s", true, "start date");
        options.addOption("b", true, "begin time");
        options.addOption("e", true, "end time");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        int numberOfHours = 0;
        if (cmd.hasOption("n")) {
            String value = cmd.getOptionValue("n");
            numberOfHours = Integer.parseInt(value);
        }
        String fileName = "";
        if (cmd.hasOption("f")) {
            fileName = cmd.getOptionValue("f");
        }

        boolean showHelp = cmd.hasOption("h");
        Set<DayOfWeek> lessonDays = new HashSet<>();

        if (cmd.hasOption("d")) {
            String value = cmd.getOptionValue("d");
        }

        LocalDate startDate = LocalDate.parse("2020-02-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (cmd.hasOption("s")) {
           startDate = LocalDate.parse(cmd.getOptionValue("s"));
        }

        LocalTime beginTime = LocalTime.parse("17:00", DateTimeFormatter.ofPattern("HH:mm") );
        if (cmd.hasOption("b")) {
            beginTime = LocalTime.parse(cmd.getOptionValue("b"));
        }

        LocalTime endTime = LocalTime.parse("18:30", DateTimeFormatter.ofPattern("HH:mm") );
        if (cmd.hasOption("e")) {
            endTime = LocalTime.parse(cmd.getOptionValue("e"));
        }

        return new LessonParameters.Builder(beginTime, endTime, numberOfHours, Set.of(), startDate).fileName(fileName).showHelp(showHelp).build();

    }

}