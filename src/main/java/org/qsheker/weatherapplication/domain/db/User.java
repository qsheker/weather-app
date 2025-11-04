package org.qsheker.weatherapplication.domain.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.qsheker.weatherapplication.domain.ObserverType;

@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "city_name")
    private String cityName;

    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private ObserverType observerType;
}
