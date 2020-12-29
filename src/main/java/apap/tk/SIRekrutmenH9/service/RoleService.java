package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> findAll();
    RoleModel findRoleById(Long id);
}
