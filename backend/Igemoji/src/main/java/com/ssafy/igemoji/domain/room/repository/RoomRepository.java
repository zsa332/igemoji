package com.ssafy.igemoji.domain.room.repository;

import com.ssafy.igemoji.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
