package com.tlcn.sportsnet_backend.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "facilities")
public class Facility {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String name; // Tên khu/CLB cầu lông có nhiều sân

    String address; // Địa chỉ chi tiết
    Double latitude;
    Double longitude;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    List<String> imageUrls; // Ảnh khu

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Court> courts = new HashSet<>();
}