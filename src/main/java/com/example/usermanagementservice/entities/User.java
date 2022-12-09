package com.example.usermanagementservice.entities;

import com.example.usermanagementservice.dtos.requests.UserDetailsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@NamedNativeQueries({
  @NamedNativeQuery(
    name = "getUserDetailsProc",
    query = "call getUserDetailsProc(:userEmail)",
    resultSetMapping = "UserDetailsDto"
  ),
    @NamedNativeQuery(
        name = "getAllUsersProc",
        query = "call getAllUsersProc(:toSkip, :pageCount)",
        resultSetMapping = "UserDetailsDto"
    ),
    @NamedNativeQuery(
        name = "deleteMyAccountProc",
        query = "call deleteMyAccountProc(:userEmail)"
    )
})
@SqlResultSetMappings({
  @SqlResultSetMapping(
    name = "UserDetailsDto",
    classes = @ConstructorResult(
      targetClass = UserDetailsDto.class,
      columns = {
          @ColumnResult(name="email", type = String.class),
          @ColumnResult(name="phone_number", type = String.class),
          @ColumnResult(name="first_name", type = String.class),
          @ColumnResult(name="last_name", type = String.class),
          @ColumnResult(name="id", type = Long.class)
      }
    )
  )
})
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "first_name", nullable = false, length = 255)
  private String firstName;
  @Column(name = "last_name", nullable = false, length = 255)
  private String lastName;
  @Column(name = "email", nullable = false, length = 255)
  private String email;
  @Column(name = "phone_number", nullable = false, length = 255)
  private String phoneNumber;
  @Column(name = "password", nullable = false, length = 255)
  private String password;


}
