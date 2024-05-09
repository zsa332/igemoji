package com.ssafy.igemoji.domain.room.repository;

import com.ssafy.igemoji.domain.room.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r " +
            "FROM Room r " +
            "JOIN FETCH r.memberList " +
            "WHERE r.id = :roomId")
    Optional<Room> findByIdByFetch(Integer roomId);

    @Query("SELECT r " +
            "FROM Room r " +
            "LEFT JOIN r.memberList m " +
            "GROUP BY r.id " +
            "HAVING COUNT(m) < r.maxNum ")
    Optional<Room> findFastRoom();
}
