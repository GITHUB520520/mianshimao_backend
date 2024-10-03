package com.project.interview;


import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.core.DockerClientBuilder;

import java.io.IOException;
import java.util.List;

import static com.github.dockerjava.api.model.HostConfig.newHostConfig;

/**
 * 测试docker连接
 */
public class DockerClientService {
    /**
     * 连接docker服务器
     * @return
     */
    public DockerClient connectDocker(){
        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://192.168.23.134:2375").build();
        Info info = dockerClient.infoCmd().exec();
        String infoStr = JSONObject.toJSONString(info);
        System.out.println("docker的环境信息如下：=================");
        System.out.println(info);
        return dockerClient;
    }

    /**
     * 创建容器
     * @param client
     * @return
     */
    public CreateContainerResponse createContainers(DockerClient client,String containerName,String imageName,Integer rqport,Integer szport){
//        映射端口8080—>80
//        ExposedPort tcp80 = ExposedPort.tcp(rqport);
//        Ports portBindings = new Ports();
//        portBindings.bind(tcp80, Ports.Binding.bindPort(szport));
//        CreateContainerResponse container = client.createContainerCmd(imageName)
//                .withName(containerName)
//                .withHostConfig(newHostConfig().withPortBindings(portBindings))
//                .withExposedPorts(tcp80).exec();
        CreateContainerResponse container = client.createContainerCmd(imageName)
                .withName(containerName).exec();
        return container;
    }
    /**
     * TODO 映射端口
     */
    public CreateContainerResponse createPortContainers(DockerClient client,String containerName,String imageName,Integer rqport,Integer szport){

        ExposedPort tcp80 = ExposedPort.tcp(rqport);
        Ports portBindings = new Ports();
        portBindings.bind(tcp80, Ports.Binding.bindPort(szport));
        CreateContainerResponse container = client.createContainerCmd(imageName)
                .withName(containerName)
                .withHostConfig(newHostConfig().withPortBindings(portBindings))
                .withExposedPorts(tcp80).exec();
        return container;
    }

    /**
     * 启动容器
     * @param client
     * @param containerId
     */
    public void startContainer(DockerClient client,String containerId){
        client.startContainerCmd(containerId).exec();
    }

    /**
     * 停止容器
     * @param client
     * @param containerId
     */
    public void stopContainer(DockerClient client,String containerId){
        client.stopContainerCmd(containerId).exec();
    }

    /**
     * 删除容器
     * @param client
     * @param containerId
     */
    public void removeContainer(DockerClient client,String containerId){
        client.removeContainerCmd(containerId).exec();
    }
    public static void main(String[] args) throws IOException {
        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://192.168.23.134:2375").build();

        // 示例：列出所有容器
        List<Container> containers = dockerClient.listContainersCmd().exec();
        for (Container container : containers) {
            System.out.println(container.getId() + ": " + container.getImage());
        }

        // 关闭Docker客户端
        dockerClient.close();
    }

}