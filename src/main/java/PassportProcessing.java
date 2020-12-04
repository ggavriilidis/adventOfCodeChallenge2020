import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * --- Day 4: Passport Processing ---
 * You arrive at the airport only to realize that you grabbed your North Pole Credentials instead of your passport.
 * While these documents are extremely similar, North Pole Credentials aren't issued by a country and therefore
 * aren't actually valid documentation for travel in most of the world.
 *
 * It seems like you're not the only one having problems, though; a very long line has formed for the automatic
 * passport scanners, and the delay could upset your travel itinerary.
 *
 * Due to some questionable network security, you realize you might be able to solve both of these problems at the
 * same time.
 *
 * The automatic passport scanners are slow because they're having trouble detecting which passports have all
 * required fields. The expected fields are as follows:
 *
 * byr (Birth Year)
 * iyr (Issue Year)
 * eyr (Expiration Year)
 * hgt (Height)
 * hcl (Hair Color)
 * ecl (Eye Color)
 * pid (Passport ID)
 * cid (Country ID)
 * Passport data is validated in batch files (your puzzle input). Each passport is represented as a sequence of
 * key:value pairs separated by spaces or newlines. Passports are separated by blank lines.
 *
 * Here is an example batch file containing four passports:
 *
 * ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
 * byr:1937 iyr:2017 cid:147 hgt:183cm
 *
 * iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
 * hcl:#cfa07d byr:1929
 *
 * hcl:#ae17e1 iyr:2013
 * eyr:2024
 * ecl:brn pid:760753108 byr:1931
 * hgt:179cm
 *
 * hcl:#cfa07d eyr:2025 pid:166559648
 * iyr:2011 ecl:brn hgt:59in
 * The first passport is valid - all eight fields are present. The second passport is invalid - it is missing hgt
 * (the Height field).
 *
 * The third passport is interesting; the only missing field is cid, so it looks like data from North Pole
 * Credentials, not a passport at all! Surely, nobody would mind if you made the system temporarily ignore missing
 * cid fields. Treat this "passport" as valid.
 *
 * The fourth passport is missing two fields, cid and byr. Missing cid is fine, but missing any other field is not,
 * so this passport is invalid.
 *
 * According to the above rules, your improved system would report 2 valid passports.
 *
 * Count the number of valid passports - those that have all required fields. Treat cid as optional. In your batch
 * file, how many passports are valid?
 *
 * --- Part Two ---
 * The line is moving more quickly now, but you overhear airport security talking about how passports with invalid
 * data are getting through. Better add some data validation, quick!
 *
 * You can continue to ignore the cid field, but each other field has strict rules about what values are valid for
 * automatic validation:
 *
 * byr (Birth Year) - four digits; at least 1920 and at most 2002.
 * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
 * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
 * hgt (Height) - a number followed by either cm or in:
 * If cm, the number must be at least 150 and at most 193.
 * If in, the number must be at least 59 and at most 76.
 * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
 * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
 * pid (Passport ID) - a nine-digit number, including leading zeroes.
 * cid (Country ID) - ignored, missing or not.
 * Your job is to count the passports where all required fields are both present and valid according to the above
 * rules. Here are some example values:
 */
public class PassportProcessing {

    List<String> passports;

    public PassportProcessing() {
        this.passports = processFile();
    }

    public long countPassportsWithMandatoryFieldsPresent() {
        return passports.stream().filter(this::containsMandatoryFields).count();
    }

    public long countValidPassports() {
        return passports.stream().
            filter(this::containsMandatoryFields)
            .map(p -> p.split(" "))
            .map(Passport::new)
            .filter(Passport::isValid)
            .count();
    }

    private List<String> processFile() {
        try {
            Path path = Paths.get(getClass().getClassLoader()
                .getResource("passport-processing.txt").toURI());
            return Arrays.asList(Files.lines(path)
                .collect(Collectors.joining("\n")).split("(?m)^\\n"))
                .stream()
                .map(s -> s.replaceAll("\\n", " "))
                .collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean containsMandatoryFields(String passport) {
        List<String> requiredFields = Arrays.asList(
            "byr",
            "iyr",
            "eyr",
            "hgt",
            "hcl",
            "ecl",
            "pid"
        );
        return requiredFields.stream().allMatch(passport::contains);
    }

    private static class Passport {
        List<Field> fields;

        private static final Map<String,String> validationRules = new HashMap<String,String>()
        {{
            put("byr", "(19[2-8][0-9]|199[0-9]|200[0-2])");
            put("iyr", "(201[0-9]|2020)");
            put("eyr", "(202[0-9]|2030)");
            put("hgt",  "(1[5-8][0-9]cm|19[0-3]cm|59in|6[0-9]in|7[0-6]in)");
            put("hcl",  "#[0-9a-f]{6}");
            put("ecl",  "(amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth)");
            put("pid",  "[0-9]{9}$");
        }};

        public Passport(String[] fields) {
            this.fields = Arrays.stream(fields).map(this::parseField).collect(Collectors.toList());
        }

        boolean isValid() {
            return fields.stream()
                .filter(f -> !("cid").equals(f.name))
                .allMatch(f -> f.value.matches(validationRules.get(f.name)));
        }

        private Field parseField(String field) {
            String[] nameValue = field.split(":");
            return new Field(nameValue[0], nameValue[1]);
        }

        private static class Field {
            private String name;
            private String value;

            public Field(String name, String value) {
                this.name = name;
                this.value = value;
            }
        }

    }
}