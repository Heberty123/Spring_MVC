package transacao.Models;

import java.util.stream.Collectors;
import java.util.*;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import transacao.Service.ConfigImg;

@Getter
@Setter
@Entity
public class Usuario implements UserDetails {

	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	private String nome;
	@OneToMany(mappedBy="usuario")
	private List<Importacao> importacoes = new ArrayList<Importacao>();

	@Lob
	@Type(type = "org.hibernate.type.ImageType")
	private byte[] image;



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Arrays.stream(role.split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() { return this.password; }

	@Override
	public String getUsername() { return this.username; }


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	public String showPhoto(){
		return ConfigImg.imgToModel(this);
	}


	public static Usuario generatedUser(String username, String email, String senha, PasswordEncoder passwordEncoder) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setNome(username);
		usuario.setEmail(email);
		usuario.setRole(Role.USER.getNome());
		usuario.setPassword(passwordEncoder.encode(senha));
		/*
		ConfigImg.attributeImg(usuario);
		*/

		return usuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, nome, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(nome, other.nome)
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}

}
