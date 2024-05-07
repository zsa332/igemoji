package com.ssafy.igemoji.domain.room.repository;

import com.ssafy.igemoji.domain.room.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("select r " +
            "from Room r " +
            "join fetch r.memberList " +
            "where r.id = :roomId")
    Optional<Room> findByIdByFetch(Integer roomId);
}
