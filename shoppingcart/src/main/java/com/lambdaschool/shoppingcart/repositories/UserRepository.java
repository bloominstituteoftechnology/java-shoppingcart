package com.lambdaschool.shoppingcart.repositories;

import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.views.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository
        extends CrudRepository<User, Long>
{
    User findByUsername(String username);



    /**
     * Custom Query finding the users and the count of their emails.
     * nativeQuery is set to true. This means the query will be using the actual database names of these. A preference of mine.
     * native queries are using faster
     * can lose portability to other databases if care is not taken in naming conventions
     *
     * @return List (element type is the interface UserNameCountEmails) containing usernames and the number of emails they have - not counting the primary email
     */


    /**
     * Counts the number of user role combinations for the given userid and roleid. Answer should be only 0 or 1.
     *
     * @param userid The userid of the user of the user role combination to check
     * @param roleid The roleid of the role of the user role combination to check
     * @return A single number, a count
     */
    @Query(value = "SELECT COUNT(*) as count FROM userroles WHERE userid = :userid AND roleid = :roleid",
            nativeQuery = true)
    JustTheCount checkUserRolesCombo(
            long userid,
            long roleid);

    /**
     * Deletes the given user, role combination
     *
     * @param userid The user id of the user of this user role combination
     * @param roleid The role id of the role of this user role combination
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UserRoles WHERE userid = :userid AND roleid = :roleid")
    void deleteUserRoles(
            long userid,
            long roleid);

    /**
     * Inserts the new user role combination
     *
     * @param uname  The username (String) of the user adding the record
     * @param userid The user id of the user of this user role combination
     * @param roleid The role id of the role of this user role combination
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO userroles(userid, roleid, created_by, created_date, last_modified_by, last_modified_date) VALUES (:userid, :roleid, :uname, CURRENT_TIMESTAMP, :uname, CURRENT_TIMESTAMP)",
            nativeQuery = true)
    void insertUserRoles(
            String uname,
            long userid,
            long roleid);
}
