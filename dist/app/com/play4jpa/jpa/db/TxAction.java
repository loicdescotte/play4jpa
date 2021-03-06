package com.play4jpa.jpa.db;

import play.libs.F;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.SimpleResult;

/**
 * Wraps an action in a JPA transaction.
 *
 * @author Jens (mail@jensjaeger.com)
 */
public class TxAction extends Action<Tx> {

    @Override
    public play.libs.F.Promise<play.mvc.SimpleResult> call(final Context ctx) throws Throwable {
        return Db.withTx(
                configuration.value(),
                configuration.readOnly(),
                new play.libs.F.Function0<F.Promise<SimpleResult>>() {
                    @Override
                    public F.Promise<SimpleResult> apply() throws Throwable {
                        return delegate.call(ctx);
                    }
                }
        );
    }
}
