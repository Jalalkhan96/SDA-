public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();

        HexaObserver hexaObserver = new HexaObserver(subject); 
        OctalObserver octalObserver = new OctalObserver(subject);
        BinaryObserver binaryObserver = new BinaryObserver(subject);

        
        subject.setState(15); 

        System.out.println("First state change: 15");  
        System.out.println("Second state change: 10");
        subject.setState(10); 
        System.out.println("Third state change: 5");
        subject.setState(5);

        subject.detach(binaryObserver);
        
        System.out.println("Fourth state change: 20");
        subject.setState(20); 

     

        System.out.println("Fifth state change: 30"); 
        subject.setState(30);
    }
}