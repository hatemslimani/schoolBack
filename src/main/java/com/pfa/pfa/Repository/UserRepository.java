package com.pfa.pfa.Repository;

import com.pfa.pfa.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.userName=:username ")
    User findByEmail(String username);
    @Query("select u.id from User u where u.role='ENSEIGNANT'")
    List<Integer> enseignantSignIn();
    @Query("select u.id from User u where u.role='RESPONSABLE'")
    List<Integer> getdepartementResponsable();
    @Query("select e from User e where e.id=:idEnsei")
    User getEnseigant(int idEnsei);
    @Query("select u from User u where u.id=:iddep")
    User getResponsableByDepartement(int iddep);
    @Query("select  u from User u where u.role='ADMIN'")
    User getAdmin();
}

