 class InsufficientFundsException extends Exception {
     public InsufficientFundsException(double amount) {
         super("Not enough funds. Short by $" + amount);
     }
 }
 
 // Use it:
 public void withdraw(double amount) throws InsufficientFundsException {
     if (amount > balance) {
         throw new InsufficientFundsException(amount - balance);
     }
     balance -= amount;
 }
 
 