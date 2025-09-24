package org.example;

import org.example.entity.Employee;

import java.util.*;

public class Main {

    /**
     * Listede birden fazla geçen (id bazlı) çalışanları bulur; her birini 1 kez döndürür.
     * NULL kayıtları yok sayar.
     * Sıra: ilk görülen duplicate id'lerin sırası (LinkedHashMap ile korunur).
     */
    public static List<Employee> findDuplicates(List<Employee> input) {
        Map<Employee, Integer> count = new LinkedHashMap<>();
        for (Employee e : input) {
            if (e == null) continue;                 // null'ı dikkate alma
            count.merge(e, 1, Integer::sum);
        }
        List<Employee> result = new ArrayList<>();
        for (Map.Entry<Employee, Integer> en : count.entrySet()) {
            if (en.getValue() > 1) result.add(en.getKey());
        }
        return result;
    }

    /**
     * Tekil temsillerden oluşan bir map döner: key = Integer id, value = Employee.
     * Aynı id tekrar gelirse ilk görüleni korur. NULL ve id=null atlanır.
     */
    public static Map<Integer, Employee> findUniques(List<Employee> input) {
        Map<Integer, Employee> uniques = new LinkedHashMap<>();
        for (Employee e : input) {
            if (e == null || e.getId() == null) continue;
            int key = e.getId().intValue();
            uniques.putIfAbsent(key, e);
        }
        return uniques;
    }

    /**
     * Birden fazla geçenlerin hepsini eler; yalnızca tam 1 kez görünenleri döndürür.
     * NULL kayıtları her koşulda atlar (test tek eleman "Burak" bekliyor).
     * Sıralama korunur.
     */
    public static List<Employee> removeDuplicates(List<Employee> input) {
        Map<Employee, Integer> count = new LinkedHashMap<>();
        for (Employee e : input) {
            if (e == null) continue;                 // null'ı tamamen dışla
            count.merge(e, 1, Integer::sum);
        }
        List<Employee> singles = new ArrayList<>();
        for (Map.Entry<Employee, Integer> en : count.entrySet()) {
            if (en.getValue() == 1) singles.add(en.getKey());
        }
        return singles;
    }

    // opsiyonel main
    public static void main(String[] args) {
        LinkedList<Employee> employees = new LinkedList<>();
        employees.add(new Employee(1, "Dogancan", "Kinik"));
        employees.add(new Employee(1, "Dogancan", "Kinik"));
        employees.add(new Employee(2, "Seyyit Battal", "Arvas"));
        employees.add(new Employee(2, "Seyyit Battal", "Arvas"));
        employees.add(new Employee(3, "Anil", "Ensari"));
        employees.add(new Employee(3, "Anil", "Ensari"));
        employees.add(new Employee(4, "Burak", "Cevizli"));
        employees.add(null);

        System.out.println(findDuplicates(employees)); // [Dogancan, Seyyit Battal, Anil]
        System.out.println(findUniques(employees));    // {1=Dogancan, 2=Seyyit..., 3=Anil, 4=Burak}
        System.out.println(removeDuplicates(employees)); // [Burak]
    }
}
