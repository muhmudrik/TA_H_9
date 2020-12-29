package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.RoleModel;
import apap.tk.SIRekrutmenH9.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDb roleDb;

    @Override
    public List<RoleModel> findAll(){
        return roleDb.findAll();
    }

    @Override
    public RoleModel findRoleById(Long id) {
        System.out.println(id);
        return roleDb.findById(id).get();

    }

}
