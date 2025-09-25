package lk.ijse.drivingschool.bo.custom;

import lk.ijse.drivingschool.bo.SuperBO;
import lk.ijse.drivingschool.dto.UserDTO;
import lk.ijse.drivingschool.entity.User;
import java.util.List;

public interface UserBO extends SuperBO {
    List<UserDTO> getAllUser();
    boolean saveUser(UserDTO userDTO);
    boolean updateUser(UserDTO userDTO);
    UserDTO getOneUser(String id);
    boolean deleteUser(String id);
}
