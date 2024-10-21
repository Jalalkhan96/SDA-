public class HexaObserver extends Observer {
   private boolean isActive = false; // isActive variable is already here!

   public HexaObserver(Subject subject) {
       this.subject = subject;
       this.subject.attach(this);
   }

   @Override
   public void update() {
       if (isActive) {  // Check if active before updating
           System.out.println("Hex String: " + Integer.toHexString(subject.getState()).toUpperCase()); 
       }
   }

   
}