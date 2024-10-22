public class BinaryObserver extends Observer {
   private boolean isActive = true;

   public BinaryObserver(Subject subject) {
       this.subject = subject;
       this.subject.attach(this);
   }

   @Override
   public void update() {
       if (isActive) {  // Check if active before updating
           System.out.println("Binary String: " + Integer.toBinaryString(subject.getState()));
       }
   }


}