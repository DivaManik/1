package org.kehadiransiswa.managers;

import org.kehadiransiswa.data.AttendanceRecord;
import org.kehadiransiswa.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceManager {
    private
    List<AttendanceRecord> attendanceRecords;

    public AttendanceManager() {

    }

    // Add methods for attendance recording and reporting
    public boolean recordAttendance(int classId, int userId, String status) {
        AttendanceRecord newAttendaceRecord = new AttendanceRecord(attendanceRecords.size() + 1, classId, userId, status);
        for (AttendanceRecord record :
                attendanceRecords) {
            if (record.getClassId() == newAttendaceRecord.getClassId()
                    && record.getUserId() != newAttendaceRecord.getUserId()) {
                attendanceRecords.add(newAttendaceRecord);
                return true;
            }
        }
        return false;
    }

}
