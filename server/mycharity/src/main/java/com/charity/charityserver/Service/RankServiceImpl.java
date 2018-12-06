package com.charity.charityserver.Service;

import com.charity.charityserver.Dao.BlockRepository;
import com.charity.charityserver.Dao.CharityItemRepository;
import com.charity.charityserver.Dao.UserRepository;
import com.charity.charityserver.Entity.Block;
import com.charity.charityserver.Entity.CharityItem;
import com.charity.charityserver.Entity.User;
import com.charity.charityserver.Pojo.CodeMsg;
import com.charity.charityserver.Pojo.Rank.ProjectResp;
import com.charity.charityserver.Pojo.Rank.UserReq;
import com.charity.charityserver.Pojo.Rank.UserResp;
import com.charity.charityserver.Pojo.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharityItemRepository charityItemRepository;

    @Override
    public ResponseResult doUser(UserReq userReq) {

        // 参数校验
        if(!("1".equals(userReq.getType()) || "2".equals(userReq.getType()))){
            ResponseResult responseResult = new ResponseResult();
            responseResult.setMsg(CodeMsg.param_msg);
            responseResult.setCode(CodeMsg.param_code);
            return responseResult;
        }
        logger.info("OK:参数校验");

        // 返回值
        List<UserResp> userRespList = new ArrayList<>();
        logger.info("OK:返回值");

        // 查询所有结果
        List<Block> blockList = blockRepository.findAll();
        for(Block block : blockList){
            UserResp userResp = new UserResp();
            userResp.setPoeid(block.getPoeid());
            userResp.setMoney(block.getMoney());
            User user = userRepository.findUserByUsername(block.getUsername());
            userResp.setInfo(user.getInfo());
            userResp.setName(user.getName());
            if("1".equals(userReq.getType())){
                // 企业用户排行榜
                if(user.getType().equals("3")){
                    userRespList.add(userResp);
                }
            }else {
                // 个人用户排行榜
                if(user.getType().equals("4")){
                    userRespList.add(userResp);
                }
            }
        }
        logger.info("OK:查询所有结果");

        // 将结果排序并取前十
        userRespList.sort(new Comparator<UserResp>() {
            @Override
            public int compare(UserResp o1, UserResp o2) {
                if(o1.getMoney() < o2.getMoney()){
                    return 1;
                }
                return -1;
            }
        });
        userRespList = userRespList.subList(0, Math.min(10, userRespList.size()));
        logger.info("OK:将结果排序并取前十");

        // 返回结果
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        responseResult.setData(userRespList);
        logger.info("OK:返回结果");

        return responseResult;
    }

    @Override
    public ResponseResult doProject() {

        // 查询所有慈善项目
        List <CharityItem> charityItemList = charityItemRepository.findAll();
        logger.info("OK:查询所有慈善项目");

        // 构造前端需要格式
        List<ProjectResp> projectRespList = new ArrayList<>();
        for(CharityItem charityItem : charityItemList){
            ProjectResp projectResp = new ProjectResp();

            projectResp.setInfo(charityItem.getInfo());
            projectResp.setMoney(charityItem.getMoney());
            projectResp.setName(charityItem.getName());
            projectResp.setOwner(charityItem.getOwner());
            projectResp.setPoeid(charityItem.getPoeid());
            projectResp.setStatus(charityItem.getState());

            projectRespList.add(projectResp);
        }
        projectRespList.sort(new Comparator<ProjectResp>() {
            @Override
            public int compare(ProjectResp o1, ProjectResp o2) {
                if(o1.getMoney() > o2.getMoney()){
                    return  1;
                }
                return -1;
            }
        });
        projectRespList = projectRespList.subList(0, Math.min(10, projectRespList.size()));
        logger.info("OK:构造前端需要格式");

        // 返回结果
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMsg(CodeMsg.success_msg);
        responseResult.setCode(CodeMsg.success_code);
        responseResult.setData(projectRespList);
        logger.info("OK:返回结果");

        return responseResult;
    }
}
