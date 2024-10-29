package com.TD.BL_Monolith_TD.infrastructure.abstract_services;

import java.util.List;

public interface CrudService<RQ,RS,ID>{

    public RS create(RQ request);
    public RS update(ID id,RQ request);
    public List<RS> getAll();
    public RS find(ID id);
    public void delete(ID id);
}
