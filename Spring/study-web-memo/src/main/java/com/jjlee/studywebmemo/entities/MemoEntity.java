package com.jjlee.studywebmemo.entities;

import java.util.Date;
import java.util.Objects;

public class MemoEntity {
    private int index;
    private Date datetime;
    private String nickname;
    private String text;

    public int getIndex() {
        return index;
    }

    public MemoEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public Date getDatetime() {
        return datetime;
    }

    public MemoEntity setDatetime(Date datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public MemoEntity setNickname(String nickname) {
        this.nickname = nickname;
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
