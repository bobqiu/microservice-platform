package com.qiu.common.utils.distributeid;

import com.qiu.common.config.DistributeIdConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.AbstractUUIDGenerator;
import org.hibernate.id.Configurable;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-27
 **/
@Slf4j
@Component
public class DistributeIdGenerator extends AbstractUUIDGenerator implements Configurable {

   /* @Autowired
    DistributeIdConfig distributeIdConfig;*/


    //@Value("${distributeid.machine-id}")
    private String machineId;

    //@Value("${distributeid.data-center-id}")
    private String dataCenterId;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        InputStream in = ClassLoader.getSystemResourceAsStream("application.properties");
        Properties p = new Properties();
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.machineId = p.getProperty("distributeid.machine-id");
        this.dataCenterId = p.getProperty("distributeid.data-center-id");
    }

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        log.debug(",machineId;{},datacenterId:{}", machineId, dataCenterId);
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(Long.parseLong(machineId), Long.parseLong(dataCenterId));
        long id = snowflakeIdWorker.nextId();
        return id;
    }

    protected long getDatacenterId()  {

        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            long id;
            if (network == null) {
                id = 1;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
            }
            return id;
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            //throw new GetHardwareIdFailedException(e);
            e.printStackTrace();
        }
        return 0;
    }
}
