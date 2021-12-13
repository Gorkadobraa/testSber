package model;

import javax.persistence.*;

@Table(name = "t1")
@Entity
public class TableOne {
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

    public TableOne(String value) {
        this.value = value;
    }

    public TableOne() {
    }

    public TableOne(Long key, String value) {
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
