package com.jjlee.study_jdbc.entities;
import java.util.Date;
import java.util.Objects;

public class MemoEntity {
    private int index;
    private Date date;
    private String text;

    public int getIndex() {
        return index;
    }

    public MemoEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public MemoEntity setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getText() {
        return text;
    }

    public MemoEntity setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoEntity that = (MemoEntity) o;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
