package com.ailu.paas;

public enum PaasApi {
    SMS_SEND_VOICE {
        public String getUrl() {
            return "http://api.ailu.group/sms/sendVoiceValidCode";
        }
    },
    SMS_SEND_CONTENT {
        public String getUrl() {
            return "http://api.ailu.group/sms/sendMsg";
        }
    },
    SMS_SEND_VALIDCODE {
        public String getUrl() {
            return "http://api.ailu.group/sms/send_validcode";
        }
    },
    SMS_CHECK_VALIDCODE {
        public String getUrl() {
            return "http://api.ailu.group/sms/check_validcode";
        }
    },
    EMAIL_SEND_WITH_SUBJECT {
        public String getUrl() {
            return "http://api.ailu.group/mail/send_mail_with_subject";
        }
    };

    private PaasApi() {
    }

    public abstract String getUrl();
}
