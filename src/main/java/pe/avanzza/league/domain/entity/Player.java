package pe.avanzza.league.domain.entity;

import lombok.*;
import pe.avanzza.league.domain.vo.AuditModel;

import javax.persistence.*;

/**
 * @author Gloria R. Leyva Jerez
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "player")
public class Player extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "player_name", length = 150, nullable = false)
    private String playerName;

    @Column(name = "nationality", length = 80, nullable = false)
    private String nationality;

    @Column(name = "height", nullable = false, scale = 2)
    private Double height;

    @Column(name = "right_handed", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean rightHanded;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id",
            foreignKey = @ForeignKey(name = "team_player_fkey"))
    private Team team;
}
