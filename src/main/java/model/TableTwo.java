package model;

import javax.persistence.*;

@Entity
@Table(name = "t2")
public class TableTwo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keyy;
    @Column
    private String value;

    public Long getKey() {
        return keyy;
    }

    public void setKey(Long key) {
        this.keyy = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TableTwo(String value) {
        this.value = value;
    }

    public TableTwo() {
    }

    public TableTwo(Long key, String value) {
        this.keyy = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "TableOne{" +
                "key=" + keyy +
                ", value='" + value + '\'' +
                '}';
    }
}
