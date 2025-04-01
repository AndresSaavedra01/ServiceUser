package co.uceva.serviceuser.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message ="No puede estar vacio")
    @Size(min=2, max=20, message="El tamaño tiene que estar entre 2 y 20")
    @Column(nullable=false)
    private String nombreCompleto;

    @NotEmpty(message = "El correo es obligatorio")
    @Email(message = "Debe ser un correo electrónico válido")
    @Size(max = 100, message = "El correo no puede tener más de 100 caracteres")
    private String correo;

    @NotEmpty(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$",
            message = "La contraseña debe contener al menos una mayúscula, un número y un carácter especial"
    )
    private String contrasena;

    @NotNull(message = "La cedula es obligatoria")
    @Min(value = 10000000L, message = "La cédula debe tener al menos 8 dígitos")
    @Max(value = 9999999999L, message = "La cédula no puede tener más de 10 dígitos")
    private Long cedula; // También considera que sea null-safe

    @NotNull(message = "El telefono es obligatorio")
    @Min(value = 1000000L, message = "El teléfono debe tener al menos 7 dígitos")
    @Max(value = 999999999999999L, message = "El teléfono no puede tener más de 15 dígitos")
    @Column(nullable = false)
    private long telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    public enum Rol {
        DOCENTE,
        ESTUDIANTE,
        COORDINADOR,
        DECANO,
        ADMINISTRADOR
    }
}
