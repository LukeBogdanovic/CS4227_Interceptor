using System;

namespace Player
{

    public class CdPlayer 
    {

        private Button playButton;
        private Button stopButton;
        public sealed void notifyButtonPushed(Button button)
        {
            if (button == playButton) this.playButtonPushed(button);
            if (button == stopButton) this.stopButtonPushed(button);
        }

        public sealed void setPlayButton(Button button)
        {
            playButton = button;
        }

        public sealed void setStopButton(Button button)
        {
            stopButton = button;
        }

        public sealed void playButtonPushed(Button button)
        {
            Console.WriteLine("Play button pushed \n");
        }

        public sealed void stopButtonPushed(Button button)
        {
            Console.WriteLine("Stop button pushed \n");
        }

    }

}