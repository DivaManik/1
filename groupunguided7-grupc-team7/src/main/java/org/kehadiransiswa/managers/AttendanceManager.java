//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.kehadiransiswa.managers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.kehadiransiswa.data.AttendanceRecord;

public class AttendanceManager {
    private final Connection connection = DBConnectionManager.getConnection();
    List<AttendanceRecord> attendanceRecords;

    public AttendanceManager() {
    }

    public List<AttendanceRecord> getAttendanceRecord() {
        List<AttendanceRecord> listofattendanceManager = new ArrayList();

        try {
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM attendance_records");

            while(rs.next()) {
                int id = rs.getInt("id");
                int ClassId = rs.getInt("class_id");
                int UserId = rs.getInt("user_id");
                String Status = rs.getString("status");
                listofattendanceManager.add(new AttendanceRecord(id, ClassId, UserId, Status));
            }
        } catch (SQLException var8) {
            var8.printStackTrace();
            System.exit(1);
        }

        return listofattendanceManager;
    }

    public boolean recordAttendance(int classId, int userId, String status) {
        AttendanceRecord newAttendaceRecord = new AttendanceRecord(this.attendanceRecords.size() + 1, classId, userId, status);
        Iterator var5 = this.attendanceRecords.iterator();

        AttendanceRecord record;
        do {
            if (!var5.hasNext()) {
                return false;
            }

            record = (AttendanceRecord)var5.next();
        } while(record.getClassId() != newAttendaceRecord.getClassId() || record.getUserId() == newAttendaceRecord.getUserId());

        this.attendanceRecords.add(newAttendaceRecord);
        return true;
    }

    public static void main(String[] args) {
        AttendanceManager am = new AttendanceManager();
        Iterator var2 = am.getAttendanceRecord().iterator();

        while(var2.hasNext()) {
            AttendanceRecord a = (AttendanceRecord)var2.next();
            System.out.println(a.getStatus());
        }

    }
}
