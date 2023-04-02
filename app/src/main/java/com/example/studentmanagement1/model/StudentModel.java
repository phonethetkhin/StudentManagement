package com.example.studentmanagement1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentModel implements Parcelable {

    private String studentId;
    private String studentName;
    private String grade;
    private int gender;
    private String roomNo;
    private String fatherName;

    public StudentModel() {
    }

    public StudentModel(String studentId, String studentName, String grade, int gender, String roomNo, String fatherName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.grade = grade;
        this.gender = gender;
        this.roomNo = roomNo;
        this.fatherName = fatherName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getGrade() {
        return grade;
    }

    public int getGender() {
        return gender;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getFatherName() {
        return fatherName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.studentId);
        dest.writeString(this.studentName);
        dest.writeString(this.grade);
        dest.writeInt(this.gender);
        dest.writeString(this.roomNo);
        dest.writeString(this.fatherName);
    }

    public void readFromParcel(Parcel source) {
        this.studentId = source.readString();
        this.studentName = source.readString();
        this.grade = source.readString();
        this.gender = source.readInt();
        this.roomNo = source.readString();
        this.fatherName = source.readString();
    }

    protected StudentModel(Parcel in) {
        this.studentId = in.readString();
        this.studentName = in.readString();
        this.grade = in.readString();
        this.gender = in.readInt();
        this.roomNo = in.readString();
        this.fatherName = in.readString();
    }

    public static final Parcelable.Creator<StudentModel> CREATOR = new Parcelable.Creator<StudentModel>() {
        @Override
        public StudentModel createFromParcel(Parcel source) {
            return new StudentModel(source);
        }

        @Override
        public StudentModel[] newArray(int size) {
            return new StudentModel[size];
        }
    };
}
