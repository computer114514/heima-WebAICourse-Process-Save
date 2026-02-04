package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//@Service
//public class EmpServiceImpl implements EmpService {
//    @Autowired
//    public EmpMapper empMapper;
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1,mapper记录
//        Long total = empMapper.count();
//        //2,mapper结果
//        List<Emp> rows = empMapper.list((page-1)*pageSize,pageSize);
//        //3，封装PageResult
//        return new PageResult<Emp>(total,rows);
//    }
//}

//pagehelper实现分页
//@Service
//public class EmpServiceImpl implements EmpService {
//    @Autowired
//    public EmpMapper empMapper;
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //控制分页参数(ph)
//        PageHelper.startPage(page,pageSize);
//        //执行
//        List<Emp> empList = empMapper.list(name,gender,begin,end);//这里返回的是全部的
//        //解析，封装
//        Page<Emp> p=(Page<Emp>) empList;
//        //理解为一台生产分页的机器，倒入列表值
//
//        return new PageResult<Emp>(p.getTotal(),p.getResult());
//        //启动getTotal按钮，和getResult按钮
//    }
//}@Service
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    public EmpMapper empMapper;
    @Autowired
    public EmpExprMapper empExprMapper;
    @Autowired
    public EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //控制分页参数(ph)
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //执行
        List<Emp> empList = empMapper.list(empQueryParam);//这里返回的是全部的
        //解析，封装
        Page<Emp> p=(Page<Emp>) empList;
        //理解为一台生产分页的机器，倒入列表值

        return new PageResult<Emp>(p.getTotal(),p.getResult());
        //启动getTotal按钮，和getResult按钮
    }



    //TODO transcational,交给spring进行控制事务，成功自动commit，失败自动回滚
    @Transactional(rollbackFor = {Exception.class})
    //默认是runtimeExption
    @Override
    public void save(Emp emp) {
        try {
            //p1,保存基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //p2，保存工作经历信息
            List<EmpExpr> exprList=emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //遍历，为empId赋值
                exprList.forEach(empExpr -> {empExpr.setEmpId(emp.getId());});
                empExprMapper.insertBatch(exprList);
            }

        } finally {
            //无论成功与否，都记录操作日志
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    //rollbackfor是发生什么异常的时候回滚呢？所有异常，不是运行时
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteByIds(ids);

        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //jiben
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //expr
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //将员工id转换为一个集合
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            //设置id
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> queryAllEmp() {
        List<Emp> empList=empMapper.queryAllEmp();
        return empList;
    }

    @Override
    public LoginInfo login(Emp emp) {
//        1，mapper查询
        Emp e= empMapper.selectEmpByUsernameAndPassword(emp);
//        2，判断是否存在，存在就组装。
        if(e!=null){
            log.info("员工登录成功,员工信息是{}",e);

            //生成专属令牌
            HashMap<String,Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
            //这就是在组装员工登录后返回的信息
        }
//        3，不然返回null
        return null;
    }
}


















