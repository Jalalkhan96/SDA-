public class OctalObserver extends Observer {
   private boolean isActive = true;

   public OctalObserver(Subject subject) {
       this.subject = subject;
       this.subject.attach(this);
   }

   @Override
   public void update() {
       if (isActive) { 
           System.out.println("Octal String: " + Integer.toOctalString(subject.getState()));
       }
   }

}