import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
//strChar = s.toCharArray() -> return new String(strChar)

public class HelloWorld {
    public static void main(String[] args) {
    Person person1 = new Person("jiajia", 17, "female");
    Person person2 = new Person("martha", 20, "female");
    Person person3 = new Person("yejia", 20, "female");
    List<Person> persons = Arrays.asList(person1, person2, person3);
    persons.forEach(person->System.out.println(person.getName()));
    persons.stream().filter(person->person.getName().equals("jiajia")).forEach(p->System.out.println(p.getAge()));
    Map<Integer, List<Person>> map = persons.stream().collect(Collectors.groupingBy(Person::getAge));
    Iterator it = map.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry<Integer, List<Person>> entry = (Map.Entry<Integer, List<Person>>) it.next();
        System.out.println(entry.getKey() + " " + entry.getValue().size());
    }
    Map<Boolean, List<Person>> partitionMap = persons.stream().collect(Collectors.partitioningBy(person -> person.getAge()>18));
    System.out.println("true: " + partitionMap.get(true).size());
    System.out.println("false: " + partitionMap.get(false).size());
//    List<Person> person_sorted = persons.stream().sorted((p1, p2) -> p2.getAge().compareTo(p1.getAge())).collect(Collectors.toList());
//    person_sorted.forEach(person -> System.out.println(person.getName()));
    List<Person> sortedPerson = sortPerson(persons);
    sortedPerson.forEach(person -> System.out.println(person.getName()));
    Stream stream = Stream.of("a", "b", "c");

    String[] array = new String[]{"a", "b", "c"};
    stream = Stream.of(array);
    stream = Arrays.stream(array);


    List<String> list = Arrays.asList(array);
    stream = list.stream();
    IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
    String str = stream.collect(Collectors.joining()).toString();
    System.out.println(str);

    List<String> words = Arrays.asList(new String[]{"a", "b", "c", "d"});
    List<String> upperWords = words.stream().map(String::toUpperCase).collect(Collectors.toList());
//    upperWords.forEach(upperWord -> System.out.println(upperWord));
    upperWords.forEach(System.out::println);

    Integer[] nums = new Integer[]{1, 2, 3, 4, 5, 6};
    Integer[] nums2 = Stream.of(nums).filter(n -> n%2 == 0).toArray(Integer[]::new);
    System.out.println(Arrays.toString(nums2));

    String testOptional = "What's up";
    System.out.println(length(testOptional));

    int sum = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
//    Optional sum2 = Stream.of(1, 2, 3, 4).reduce(Integer::sum);
    System.out.println(Stream.of(1, 2, 3, 4).reduce(Integer::sum));
    int testCount = countSegments("  foo  bar");
    }

    public static void println(String text) {
        Optional.ofNullable(text).ifPresent(System.out::println);
    }

    public static int length(String text) {
        return Optional.ofNullable(text).map(String::length).orElse(-1);
    }

    private static List<Person> sortPerson(List<Person> persons) {
        List<Person> person_sorted = persons.stream().sorted((p1, p2) -> p2.getAge().compareTo(p1.getAge())).collect(Collectors.toList());
        return person_sorted;

    }

    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) {
            return "";
        }
        String res = "";
        String substring = "";
        Map<String, Integer> map = new HashMap<String, Integer>();
        substring = paragraph.toLowerCase();



        String[] strs = substring.replaceAll("[.!?\\\\-]" , " ").split("\\s+");
        List<String> banString = Arrays.asList(banned);

        IntStream.range(0, strs.length).forEach(i -> System.out.println(strs[i]));

        for (int i=0; i<strs.length; i++) {
            if (map.containsKey(strs[i])) {
                int count = map.get(strs[i]);
                map.put(strs[i], count + 1);
            } else {
                map.put(strs[i], 1);
            }
        }

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        Iterator it = sortedMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> item = (Map.Entry<String, Integer>) it.next();
            if (!banString.contains(item.getKey())) {
                res = item.getKey();
                break;
            }
        }
        return res;
    }

    private static int countSegments(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String[] strs = s.split("\\s++");

        List<String> strsList = Arrays.asList(strs);
        System.out.println("start....");
        strsList.forEach(System.out::println);
        System.out.println("end....");
        return strs.length;
    }
}
