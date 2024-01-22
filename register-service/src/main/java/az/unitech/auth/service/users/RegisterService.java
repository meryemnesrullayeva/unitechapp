package az.unitech.auth.service.users;

import az.unitech.auth.dto.UserDto.*;

/**

 Interface for User related operations.
 */
public interface RegisterService {

    /**

     Get a user by their ID.
     @param id The ID of the user to retrieve.
     @return The UserDto object containing the user's data.
     */
    UserDto getUserById(Long id);

    String getPinByUserId(Long id);

    /**

     Add a new user.
     @param dto The UserDto object containing the user's data to be inserted.
     @return The UserDto object containing the user's data after insertion.
     */
    UserDto insertUser(UserDto dto);

    /**

     Reset a user's password.
     @param id The ID of the user whose password is to be reset.
     @param password The new password to set.
     */
    void resetPassword(Long id, String password);

        /**

     Reset a user's password.
     @param id The ID of the user whose password is to be reset.
     @param password The new password to set.
     */
    Long resetUserPassword(String oldPassword, String newPassword);


    /**

     Update a user's information.
     @param id The ID of the user to update.
     @param dto The UserGetDto object containing the updated user information.
     @return The UserGetDto object containing the user's updated data.
     */
    UserGetDto updateUser(Long id, UserGetDto dto);

    /**

     Delete a user.
     @param id The ID of the user to delete.
     @return A boolean indicating whether or not the deletion was successful.
     */
    boolean deleteUser(Long id);

}
