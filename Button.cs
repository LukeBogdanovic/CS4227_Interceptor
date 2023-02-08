namespace Player
{

    public class Button
    {

        private IPushCallBack iPushCallBack;
        public Button(IPushCallBack ipcb)
        {
            iPushCallBack = ipcb;
        }

        public sealed void push()
        {
            iPushCallBack.notifyButtonPushed(this);
        }

    }

}