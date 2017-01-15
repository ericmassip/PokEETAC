package business;

import entity.serviceLibraryResults.ScannedRouterResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserFloorTest {
    UserBusiness userBusiness = new UserBusiness();
    ScannedRouterResult router1;
    ScannedRouterResult router2;
    ScannedRouterResult router3;
    ScannedRouterResult router4;
    ScannedRouterResult router5;
    ScannedRouterResult router6;
    ScannedRouterResult router7;
    ScannedRouterResult router8;
    ScannedRouterResult router9;
    ScannedRouterResult router10;
    ScannedRouterResult router11;
    ScannedRouterResult router12;
    ScannedRouterResult router13;
    ScannedRouterResult router14;
    ScannedRouterResult router15;
    List<ScannedRouterResult> scannedRouters;

    @Before
    public void setUp() throws Exception {
        router1 = new ScannedRouterResult();
        router2 = new ScannedRouterResult();
        router3 = new ScannedRouterResult();
        router4 = new ScannedRouterResult();
        router5 = new ScannedRouterResult();
        router6 = new ScannedRouterResult();
        router7 = new ScannedRouterResult();
        router8 = new ScannedRouterResult();
        router9 = new ScannedRouterResult();
        router10 = new ScannedRouterResult();
        router11 = new ScannedRouterResult();
        router12 = new ScannedRouterResult();
        router13 = new ScannedRouterResult();
        router14 = new ScannedRouterResult();
        router15 = new ScannedRouterResult();
        scannedRouters = new ArrayList<>();
        scannedRouters.add(router1);
        scannedRouters.add(router2);
        scannedRouters.add(router3);
        scannedRouters.add(router4);
        scannedRouters.add(router5);
        scannedRouters.add(router6);
        scannedRouters.add(router7);
        scannedRouters.add(router8);
        scannedRouters.add(router9);
        scannedRouters.add(router10);
        scannedRouters.add(router11);
        scannedRouters.add(router12);
        scannedRouters.add(router13);
        scannedRouters.add(router14);
        scannedRouters.add(router15);
    }

    @After
    public void tearDown() throws Exception {
        this.router1 = null;
        this.router2 = null;
        this.router3 = null;
        this.router4 = null;
        this.router5 = null;
        this.router6 = null;
        this.router7 = null;
        this.router8 = null;
        this.router9 = null;
        this.router10 = null;
        this.router11 = null;
        this.router12 = null;
        this.router13 = null;
        this.router14 = null;
        this.router15 = null;
        this.scannedRouters = null;
    }

    @Test
    public void testZeroFloorZone1() throws Exception {
        router1.BSSID = "f4:1f:c2:d1:a2:90";
        router1.signalLevel = -75;
        router2.BSSID = "64:a0:e7:26:e0:90";
        router2.signalLevel = -41;
        router3.BSSID = "64:f6:9d:4a:fc:50";
        router3.signalLevel = -75;
        router4.BSSID = "64:a0:e7:28:f0:e0";
        router4.signalLevel = -44;
        router5.BSSID = "68:bc:0c:f7:a2:10";
        router5.signalLevel = -66;
        router6.BSSID = "68:bd:ab:49:07:60";
        router6.signalLevel = -77;
        router7.BSSID = "64:a0:e7:28:f7:b0";
        router7.signalLevel = -79;
        router8.BSSID = "64:f6:9d:4a:fb:c0";
        router8.signalLevel = -73;
        router9.BSSID = "08:d0:9f:17:30:00";
        router9.signalLevel = -80;
        assertEquals(0, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testZeroFloorZone2() throws Exception {
        router1.BSSID = "f4:1f:c2:d1:a2:90";
        router1.signalLevel = -53;
        router2.BSSID = "64:a0:e7:26:e0:90";
        router2.signalLevel = -65;
        router3.BSSID = "64:a0:e7:28:f0:e0";
        router3.signalLevel = -66;
        router4.BSSID = "68:bd:ab:49:07:60";
        router4.signalLevel = -66;
        router5.BSSID = "08:d0:9f:17:30:00";
        router5.signalLevel = -75;
        router6.BSSID = "e8:04:62:f6:c4:80";
        router6.signalLevel = -73;
        router7.BSSID = "08:d0:9f:86:64:f0";
        router7.signalLevel = -75;
        router8.BSSID = "64:a0:e7:28:f7:b0";
        router8.signalLevel = -69;
        router9.BSSID = "68:bc:0c:f7:a2:10";
        router9.signalLevel = -69;
        router10.BSSID = "0c:27:24:4e:3e:50";
        router10.signalLevel = -82;
        assertEquals(0, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testZeroFloorZone3() throws Exception {
        router1.BSSID = "f4:1f:c2:d1:a2:90";
        router1.signalLevel = -54;
        router2.BSSID = "64:a0:e7:26:e0:90";
        router2.signalLevel = -65;
        router3.BSSID = "68:bd:ab:49:07:60";
        router3.signalLevel = -71;
        router4.BSSID = "64:a0:e7:28:f7:b0";
        router4.signalLevel = -55;
        router5.BSSID = "68:bc:0c:f7:a2:10";
        router5.signalLevel = -76;
        router6.BSSID = "0c:27:24:4e:3e:50";
        router6.signalLevel = -77;
        router7.BSSID = "08:d0:9f:86:64:f0";
        router7.signalLevel = -71;
        router8.BSSID = "64:f6:9d:4a:f9:40";
        router8.signalLevel = -75;
        router9.BSSID = "08:d0:9f:22:9c:60";
        router9.signalLevel = -77;
        router10.BSSID = "64:a0:e7:28:f0:e0";
        router10.signalLevel = -64;
        router11.BSSID = "2c:3f:38:c1:bc:f0";
        router11.signalLevel = -75;
        router12.BSSID = "e8:04:62:f6:c4:80";
        router12.signalLevel = -82;
        assertEquals(0, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testZeroFloorZone4() throws Exception {
        router1.BSSID = "f4:1f:c2:d1:a2:90";
        router1.signalLevel = -61;
        router2.BSSID = "68:bd:ab:49:07:60";
        router2.signalLevel = -62;
        router3.BSSID = "64:a0:e7:28:f7:b0";
        router3.signalLevel = -43;
        router4.BSSID = "0c:27:24:4e:3e:50";
        router4.signalLevel = -72;
        router5.BSSID = "08:d0:9f:86:64:f0";
        router5.signalLevel = -74;
        router6.BSSID = "64:a0:e7:28:f0:e0";
        router6.signalLevel = -68;
        router7.BSSID = "68:bc:0c:f7:a2:10";
        router7.signalLevel = -77;
        router8.BSSID = "d4:d7:48:80:e0:c0";
        router8.signalLevel = -82;
        router9.BSSID = "64:a0:e7:26:e0:90";
        router9.signalLevel = -67;
        router10.BSSID = "64:f6:9d:b3:ea:f0";
        router10.signalLevel = -75;
        router11.BSSID = "08:d0:9f:17:30:00";
        router11.signalLevel = -75;
        assertEquals(0, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testFirstFloorZone1() throws Exception {
        router1.BSSID = "64:a0:e7:28:f0:e0";
        router1.signalLevel = -57;
        router2.BSSID = "68:bc:0c:f7:a2:10";
        router2.signalLevel = -67;
        router3.BSSID = "64:a0:e7:26:e0:90";
        router3.signalLevel = -61;
        router4.BSSID = "e8:04:62:f6:c4:80";
        router4.signalLevel = -66;
        router5.BSSID = "64:f6:9d:b3:ea:f0";
        router5.signalLevel = -66;
        assertEquals(1, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testFirstFloorZone2() throws Exception {
        router1.BSSID = "64:a0:e7:28:f0:e0";
        router1.signalLevel = -71;
        router2.BSSID = "64:a0:e7:26:e0:90";
        router2.signalLevel = -73;
        router3.BSSID = "e8:04:62:f6:c4:80";
        router3.signalLevel = -45;
        router4.BSSID = "2c:3f:38:c1:bc:f0";
        router4.signalLevel = -65;
        router5.BSSID = "64:f6:9d:b3:ea:f0";
        router5.signalLevel = -59;
        router6.BSSID = "68:bd:ab:49:07:60";
        router6.signalLevel = -74;
        router7.BSSID = "68:bc:0c:f7:a2:10";
        router7.signalLevel = -79;
        router8.BSSID = "0c:27:24:4e:3e:50";
        router8.signalLevel = -77;
        router9.BSSID = "08:d0:9f:86:64:f0";
        router9.signalLevel = -77;
        router10.BSSID = "68:bc:0c:f9:aa:f0";
        router10.signalLevel = -77;
        router11.BSSID = "08:d0:9f:17:30:00";
        router11.signalLevel = -79;
        router12.BSSID = "f4:1f:c2:d1:a2:90";
        router12.signalLevel = -82;
        router13.BSSID = "64:f6:9d:4d:f8:20";
        router13.signalLevel = -83;
        assertEquals(1, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testFirstFloorZone3() throws Exception {
        router1.BSSID = "e8:04:62:f6:c4:80";
        router1.signalLevel = -56;
        router2.BSSID = "2c:3f:38:c1:bc:f0";
        router2.signalLevel = -49;
        router3.BSSID = "68:bd:ab:49:07:60";
        router3.signalLevel = -71;
        router4.BSSID = "0c:27:24:4e:3e:50";
        router4.signalLevel = -79;
        router5.BSSID = "f4:1f:c2:d1:a2:90";
        router5.signalLevel = -77;
        router6.BSSID = "a4:56:30:47:a8:40";
        router6.signalLevel = -78;
        router7.BSSID = "64:f6:9d:b3:ea:f0";
        router7.signalLevel = -58;
        router8.BSSID = "68:bc:0c:f9:aa:f0";
        router8.signalLevel = -75;
        router9.BSSID = "08:d0:9f:17:30:00";
        router9.signalLevel = -72;
        router10.BSSID = "68:bc:0c:f9:aa:f0";
        router10.signalLevel = -77;
        router11.BSSID = "08:d0:9f:17:30:00";
        router11.signalLevel = -79;
        router12.BSSID = "64:a0:e7:28:f0:e0";
        router12.signalLevel = -76;
        router13.BSSID = "64:a0:e7:28:f7:b0";
        router13.signalLevel = -83;
        router14.BSSID = "64:f6:9d:b3:e9:80";
        router14.signalLevel = -79;
        router15.BSSID = "68:bc:0c:f7:a5:d0";
        router15.signalLevel = -81;
        assertEquals(1, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testFirstFloorZone4() throws Exception {
        router1.BSSID = "2c:3f:38:c1:bc:f0";
        router1.signalLevel = -59;
        router2.BSSID = "68:bd:ab:49:07:60";
        router2.signalLevel = -77;
        router3.BSSID = "0c:27:24:4e:3e:50";
        router3.signalLevel = -70;
        router4.BSSID = "64:f6:9d:b3:ea:f0";
        router4.signalLevel = -44;
        router5.BSSID = "64:a0:e7:28:f0:e0";
        router5.signalLevel = -75;
        router6.BSSID = "64:a0:e7:28:f7:b0";
        router6.signalLevel = -71;
        router7.BSSID = "d4:d7:48:80:e0:c0";
        router7.signalLevel = -81;
        router8.BSSID = "68:bc:0c:f7:a2:10";
        router8.signalLevel = -82;
        router9.BSSID = "68:bc:0c:f9:aa:f0";
        router9.signalLevel = -74;
        router10.BSSID = "08:d0:9f:86:64:f0";
        router10.signalLevel = -74;
        router11.BSSID = "e8:04:62:f6:c4:80";
        router11.signalLevel = -63;
        router12.BSSID = "68:bc:0c:f7:a5:d0";
        router12.signalLevel = -84;
        router13.BSSID = "64:f6:9d:4d:f8:20";
        router13.signalLevel = -84;
        assertEquals(1, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testSecondFloorZone1() throws Exception {
        router1.BSSID = "64:a0:e7:28:f0:e0";
        router1.signalLevel = -79;
        router2.BSSID = "68:bc:0c:f7:a5:d0";
        router2.signalLevel = -81;
        router3.BSSID = "64:a0:e7:28:a2:10";
        router3.signalLevel = -77;
        router4.BSSID = "68:bd:ab:49:07:60";
        router4.signalLevel = -82;
        router5.BSSID = "64:a0:e7:28:bc:b0";
        router5.signalLevel = -81;
        router6.BSSID = "0c:27:24:4e:4a:e0";
        router6.signalLevel = -86;
        assertEquals(2, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testSecondFloorZone2() throws Exception {
        router1.BSSID = "64:f6:9d:4d:f8:20";
        router1.signalLevel = -78;
        router2.BSSID = "e8:04:62:f6:c4:80";
        router2.signalLevel = -73;
        router3.BSSID = "68:bd:ab:49:07:60";
        router3.signalLevel = -79;
        router4.BSSID = "68:bc:0c:f9:aa:f0";
        router4.signalLevel = -77;
        router5.BSSID = "0c:27:24:4e:4a:e0";
        router5.signalLevel = -75;
        router6.BSSID = "64:a0:e7:28:a2:10";
        router6.signalLevel = -84;
        assertEquals(2, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testSecondFloorZone3() throws Exception {
        router1.BSSID = "0c:27:24:4e:4a:e0";
        router1.signalLevel = -83;
        router2.BSSID = "2c:3f:38:c1:bc:f0";
        router2.signalLevel = -77;
        router3.BSSID = "0c:27:24:4e:3e:50";
        router3.signalLevel = -79;
        router4.BSSID = "a4:56:30:47:a8:40";
        router4.signalLevel = -78;
        router5.BSSID = "e8:04:62:f6:c4:80";
        router5.signalLevel = -80;
        router6.BSSID = "68:bc:0c:f9:aa:f0";
        router6.signalLevel = -74;
        router7.BSSID = "64:f6:9d:4d:f8:20";
        router7.signalLevel = -82;
        assertEquals(2, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testSecondFloorZone4() throws Exception {
        router1.BSSID = "68:bc:0c:f7:a5:d0";
        router1.signalLevel = -81;
        router2.BSSID = "64:f6:9d:b3:ea:f0";
        router2.signalLevel = -76;
        router3.BSSID = "0c:27:24:4e:3e:50";
        router3.signalLevel = -79;
        router4.BSSID = "68:bc:0c:f9:aa:f0";
        router4.signalLevel = -74;
        router5.BSSID = "2c:3f:38:c1:bc:f0";
        router5.signalLevel = -78;
        router6.BSSID = "64:f6:9d:4d:f8:20";
        router6.signalLevel = -82;
        assertEquals(2, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testThirdFloorZone1() throws Exception {
        router1.BSSID = "0c:27:24:4e:4a:e0";
        router1.signalLevel = -61;
        router2.BSSID = "64:a0:e7:28:a2:10";
        router2.signalLevel = -76;
        router3.BSSID = "68:bc:0c:f7:a5:d0";
        router3.signalLevel = -77;
        assertEquals(3, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testThirdFloorZone2() throws Exception {
        router1.BSSID = "0c:27:24:4e:4a:e0";
        router1.signalLevel = -45;
        router2.BSSID = "68:bc:0c:f7:a5:d0";
        router2.signalLevel = -78;
        router3.BSSID = "64:f6:9d:b3:f0:70";
        router3.signalLevel = -77;
        router4.BSSID = "68:bd:ab:49:07:60";
        router4.signalLevel = -84;
        assertEquals(3, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testThirdFloorZone3() throws Exception {
        router1.BSSID = "0c:27:24:4e:4a:e0";
        router1.signalLevel = -59;
        router2.BSSID = "68:bc:0c:f7:a5:d0";
        router2.signalLevel = -76;
        router3.BSSID = "64:f6:9d:b3:f0:70";
        router3.signalLevel = -77;
        router4.BSSID = "0c:27:24:4e:3e:50";
        router4.signalLevel = -80;
        assertEquals(3, userBusiness.getUserFloor(scannedRouters));
    }

    @Test
    public void testThirdFloorZone4() throws Exception {
        router1.BSSID = "0c:27:24:4e:4a:e0";
        router1.signalLevel = -66;
        router2.BSSID = "68:bc:0c:f7:a5:d0";
        router2.signalLevel = -69;
        router3.BSSID = "68:bd:ab:49:07:60";
        router3.signalLevel = -82;
        router4.BSSID = "0c:27:24:4e:3e:50";
        router4.signalLevel = -78;
        router5.BSSID = "68:bc:0c:f9:aa:f0";
        router5.signalLevel = -78;
        assertEquals(3, userBusiness.getUserFloor(scannedRouters));
    }
}