package szh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szh.dao.DetestDao;
import szh.service.DetestService;

@Service
public class DetestServiceImpl implements DetestService {

    @Autowired(required = false)
    private DetestDao testDao;

    @Override
    public int testAction() {

        int i = testDao.selectCount();

        return i;
    }
}
