package lk.ijse.drivingschool.bo.custom.impl;

import lk.ijse.drivingschool.bo.custom.UserBO;
import lk.ijse.drivingschool.bo.util.EntityDTOConverter;
import lk.ijse.drivingschool.dao.DAOFactory;
import lk.ijse.drivingschool.dao.custom.UserDAO;
import lk.ijse.drivingschool.dto.UserDTO;
import lk.ijse.drivingschool.entity.User;

import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public List<UserDTO> getAllUser() {
        List<User> entitys = userDAO.getAll();
        return entitys.stream().map(EntityDTOConverter::toUserDTO).toList();
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(EntityDTOConverter.toUserEntity(userDTO));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.update(EntityDTOConverter.toUserEntity(userDTO));
    }

    @Override
    public UserDTO getOneUser(String id) {
        return EntityDTOConverter.toUserDTO(userDAO.getOne(id));
    }

    @Override
    public boolean deleteUser(String id) {
        return userDAO.delete(id);
    }
}
