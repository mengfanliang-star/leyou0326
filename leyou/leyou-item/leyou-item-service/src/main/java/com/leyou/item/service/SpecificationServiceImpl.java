package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecGroupExample;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.pojo.SpecParamExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroupExample specGroupExample = new SpecGroupExample();
        SpecGroupExample.Criteria criteria = specGroupExample.createCriteria();
        criteria.andCidEqualTo(cid);
        return this.specGroupMapper.selectByExample(specGroupExample);
    }

    @Override
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParamExample specParamExample = new SpecParamExample();
        SpecParamExample.Criteria criteria = specParamExample.createCriteria();
        if(gid!=null){
        criteria.andGroupIdEqualTo(gid);
        criteria.andGenericEqualTo(generic);
        }
        criteria.andCidEqualTo(cid);

        criteria.andSearchingEqualTo(searching);
        return specParamMapper.selectByExample(specParamExample);
    }
}
